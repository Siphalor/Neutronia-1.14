package team.abnormals.neutronia.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NEntityTypes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class SocialVillagerMale extends SocialVillagerBase {

	public SocialVillagerMale(World world) {
		super(NEntityTypes.SOCIAL_VILLAGER_MALE, world);
		try {
			firstName = generateFirstName();
			lastName = generateLastName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setCustomName(new StringTextComponent(firstName + " " + lastName));
		
	}

	@Override
	public PassiveEntity createChild(PassiveEntity arg0) {
		return null;
	}
	private String generateFirstName() throws IOException {
		String firstNameOut;
		Random rand = new Random();
		Identifier names = new Identifier("neutronia:names/male.txt");
		InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(names).getInputStream();
		Scanner scanner = new Scanner(stream);
		StringBuilder builder = new StringBuilder();
		while (scanner.hasNextLine()) {
			builder.append(scanner.nextLine());
			builder.append(",");
		}
		String[] strings = builder.toString().split(",");
		firstNameOut = strings[rand.nextInt(strings.length)];
		scanner.close();
		stream.close();
		return firstNameOut;
	}
	private String generateLastName() throws IOException
	{
		String lastNameOut;
		Random rand = new Random();
		Identifier surnames = new Identifier("neutronia:names/surnames.txt");
		InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(surnames).getInputStream();
		Scanner scanner = new Scanner(stream);
		StringBuilder builder = new StringBuilder();
		while (scanner.hasNextLine())
		{
			builder.append(scanner.nextLine());
			builder.append(",");
		}
		String[] strings = builder.toString().split(",");
		lastNameOut = strings[rand.nextInt(strings.length)];
		stream.close();
		scanner.close();
		return lastNameOut;
	}

}