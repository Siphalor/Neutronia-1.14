package team.hollow.neutronia.event_system.loot;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ServerWorldRedirectBoilerplateGenerator
{
	static String VARSYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public static boolean containsfield(String field, Field[] fields)
	{
		for(int i = 0; i < fields.length; i++)
		{
			Field c = fields[i];
			if(c.getName().equals(field))return true;
		}
		return false;
	}
	public static boolean containsmethod(String method, Method[] methods)
	{
		for(int i = 0; i < methods.length; i++)
		{
			Method c = methods[i];
			if(c.getName().equals(method))return true;
		}
		return false;
	}
	public static void generate()
	{
		ArrayList<String> imports = new ArrayList<String>();
		
		
		String ServerWorldRedirect = "";
		
		Class sw = ServerWorld.class;
		Class ww = World.class;
		
		Field[] fields = sw.getFields();
		Field[] fields_ww = ww.getFields();
		
		for(int i = 0; i < fields.length; i++)
		{
			Field field = fields[i];
			ServerWorldRedirect += "\n";
			if(!containsfield(field.getName(), fields_ww))ServerWorldRedirect += "//";
			ServerWorldRedirect += "this."+field.getName()+"=that."+field.getName()+";";
		}
		
		
		
		Method[] methods = sw.getMethods();
		Method[] methods_ww = ww.getMethods();
		
		for(int i = 0; i < methods.length; i++)
		{
			boolean cancel = false;
			Method method = methods[i];
			String str = "@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n@Override\n";
			int mod = method.getModifiers();
			if(
				!Modifier.isPublic(mod)	||
				Modifier.isFinal(mod) ||
				Modifier.isStatic(mod) ||
				Modifier.isAbstract(mod) || 
				Modifier.isPrivate(mod) || 
				Modifier.isProtected(mod)
					)
			{
				cancel=true;
				str += "//final/static/abstract/private/protected method\n";
			}
			if(method.getName().indexOf("$") != -1)
			{
					cancel=true;
					str += "//synthesized method\n";
			}
			
			
			String returntype = method.getReturnType().getSimpleName();
			{
				String canon = method.getReturnType().getCanonicalName();
				if(canon != null && canon.indexOf(".") != -1)
				{
					if(!imports.contains(canon))imports.add(canon);
				}
			}
			
			str += "public "+returntype+" ";
			str += method.getName()+"(";
			
			Class<?>[] params = method.getParameterTypes();
			for(int p = 0; p < params.length; p++)
			{
				Class<?> parameter = params[p];
				{
					String canon = parameter.getCanonicalName();
					if(canon != null && canon.indexOf(".") != -1)
					{
						if(!imports.contains(canon))imports.add(canon);
					}
				}
				
				
				if(p > 0)str += ", ";
				str += parameter.getSimpleName()+" "+VARSYMBOLS.charAt(p);
			}
			str += "){\n";
			
			Class<?>[] exceptionTypes = method.getExceptionTypes();
			if(exceptionTypes.length > 0)
			{
				str += "try{\n";
			}
			
			if(containsmethod(method.getName(), methods_ww))
			{
				String precall = "";
				if(returntype != "void")
				{
					precall += "return ";
				}
				str += "if(that != null)\n{\n";
				
				String call = method.getName()+"(";
				for(int p = 0; p < params.length; p++)
				{
					if(p > 0)call += ", ";
					call += VARSYMBOLS.charAt(p);
				}
				call += ");\n";
				
				
				str += precall+"that."+call;
				str += "}\nelse\n{\n";
				str += precall+"super."+call;
				str += "}\n";
			}else
			{
				if(returntype != "void")
				{
					
					str += "//non void return in non-World method";
					cancel=true;
				}
			}
			
			if(exceptionTypes.length > 0)
			{
				str += "}catch(Exception e){}\n";
			}
			
			str += "}\n";
			if(str.indexOf("MinecraftServer") != -1)cancel=true;
			if(cancel)str = "/*\n"+str+"\n*/\n";
			
			ServerWorldRedirect += "\n"+str;
		}
		
		
		String impstr = "";
		for(int i =0; i < imports.size(); i++)
		{
			impstr += "\nimport "+imports.get(i)+";";
		}
		ServerWorldRedirect = impstr+"\n\n\n"+ServerWorldRedirect;
		BufferedWriter writer;
		try
		{
			writer = new BufferedWriter(new FileWriter("F:/dump.txt"));
			
				writer.write(ServerWorldRedirect);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("generatred");
	}
}
