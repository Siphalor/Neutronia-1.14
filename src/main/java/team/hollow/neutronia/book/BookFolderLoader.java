package team.hollow.neutronia.book;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class BookFolderLoader {

	public static File loadDir;
	
	public static void setup(File instanceDir) {
		loadDir = new File(instanceDir, "guidebooks");
		if(!loadDir.exists())
			loadDir.mkdir();
		else if(!loadDir.isDirectory())
			throw new RuntimeException(loadDir.getAbsolutePath() + " is a file, not a folder, aborting. Please delete this file or move it elsewhere if it has important contents.");
	}

	public static void findBooks() {
		Optional<ModContainer> mod = FabricLoader.getInstance().getModContainer("neutronia");
		File[] subdirs = loadDir.listFiles(File::isDirectory);
		for(File dir : subdirs) {
			File bookJson = new File(dir, "book.json");
			if(bookJson.exists()) {
				try {
					Identifier res = new Identifier(Neutronia.MOD_ID, dir.getName());
					FileInputStream stream = new FileInputStream(bookJson);
					BookRegistry.INSTANCE.loadBook(mod.get(), res, stream, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
