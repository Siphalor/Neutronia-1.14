package team.hollow.neutronia.dataPackModding;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class NotebookFolderLoader {

	public static File loadDir;
	
	public static void setup(File instanceDir) {
		loadDir = new File(instanceDir, NotebookRegistry.BOOKS_LOCATION);
		if(!loadDir.exists())
			loadDir.mkdir();
		else if(!loadDir.isDirectory())
			throw new RuntimeException(loadDir.getAbsolutePath() + " is a file, not a folder, aborting. Please delete this file or move it elsewhere if it has important contents.");
	}
	
	static void findBooks() {
		for(ModContainer mod : FabricLoader.getInstance().getAllMods()) {
			for(File dir : Objects.requireNonNull(loadDir.listFiles(File::isDirectory))) {
				File bookJson = new File(dir, ".json");
				if(bookJson.exists()) {
					try {
						Identifier res = new Identifier(Neutronia.MOD_ID, dir.getName());
						FileInputStream stream = new FileInputStream(bookJson);
//						NotebookRegistry.INSTANCE.test(mod, res, stream, true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}