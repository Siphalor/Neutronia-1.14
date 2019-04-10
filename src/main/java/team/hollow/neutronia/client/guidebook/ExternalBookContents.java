package team.hollow.neutronia.client.guidebook;

import net.minecraft.util.Identifier;
import org.apache.commons.io.FilenameUtils;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.notebook.Notebook;
import team.hollow.neutronia.notebook.NotebookFolderLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExternalBookContents extends BookContents {

    public ExternalBookContents(Notebook book) {
        super(book);
    }

    @Override
    protected void findFiles(String dir, List<Identifier> list) {
        File root = new File(NotebookFolderLoader.loadDir, book.resourceLoc.getPath());
        File enUs = new File(root, DEFAULT_LANG);
        if (enUs.exists()) {
            File searchDir = new File(enUs, dir);
            if (searchDir.exists())
                crawl(searchDir, searchDir, list);
        }
    }

    private void crawl(File realRoot, File root, List<Identifier> list) {
        File[] files = root.listFiles();
        for (File f : files) {
            if (f.isDirectory())
                crawl(realRoot, f, list);
            else if (f.getName().endsWith(".json"))
                list.add(relativize(realRoot, f));
        }
    }

    private Identifier relativize(File root, File f) {
        String rootPath = root.getAbsolutePath();
        String filePath = f.getAbsolutePath().substring(rootPath.length() + 1);
        String cleanPath = FilenameUtils.removeExtension(FilenameUtils.separatorsToUnix(filePath));

        return new Identifier(Neutronia.MOD_ID, cleanPath);
    }

    @Override
    protected InputStream loadJson(Identifier resloc, Identifier fallback) {
        String realPath = resloc.getPath().substring(NotebookFolderLoader.loadDir.getName().length());
        File targetFile = new File(NotebookFolderLoader.loadDir, realPath);

        if (targetFile.exists()) {
            FileInputStream stream;
            try {
                stream = new FileInputStream(targetFile);
                return stream;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fallback != null) {
            new RuntimeException("Patchouli failed to load " + resloc + ". Switching to fallback.").printStackTrace();
            return loadJson(fallback, null);
        }

        return null;
    }

}