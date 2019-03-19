package team.hollow.neutronia.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
* The vertex and fragment shaders are setup when the box object is
* constructed. They are applied to the GL state prior to the box
* being drawn, and released from that state after drawing.
* @author Stephen Jones with edits by NurMarvin (Marvin Witt)
*/
public class ShaderUtil {

    private int program;
    private float time;

    public ShaderUtil(String fragmentShaderString) {
        this(fragmentShaderString, "#version 330 core\r\n" + "\r\n" + "in vec3 position;\r\n" + "\r\n" + "void main()\r\n" + "{\r\n" + "	gl_Position = vec4(position, 1.0);\r\n" + "}");
    }

    /**
     * Creates a new ShaderUtil with the given fragmentShaderString and vertexShaderString
     * @param fragmentShaderString The fragment shader code
     * @param vertexShaderString The vertex shader code
     */
    public ShaderUtil(String fragmentShaderString, String vertexShaderString) {
        int vertexShader, fragmentShader;

        try {
            vertexShader = createShader(vertexShaderString, ARBVertexShader.GL_VERTEX_SHADER_ARB);
            fragmentShader = createShader(fragmentShaderString, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
        } catch (Exception exc) {
            exc.printStackTrace();
            return;
        }

        if(vertexShader == 0 || fragmentShader == 0) return;

        program = ARBShaderObjects.glCreateProgramObjectARB();

        if (program == 0) return;

        ARBShaderObjects.glAttachObjectARB(program, vertexShader);
        ARBShaderObjects.glAttachObjectARB(program, fragmentShader);

        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(program));
            return;
        }

        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(program));
        }
    }

    public void draw() {
        time+=0.01;
        Window window = MinecraftClient.getInstance().window;

        GL11.glPushMatrix();

        GL20.glUseProgram(program);
        GL20.glUniform2f(GL20.glGetUniformLocation(program, "resolution"), (float) window.getScaledWidth(), (float) window.getScaledHeight()); //So setzt man ne uniform
        GL20.glUniform1f(GL20.glGetUniformLocation(program, "time"), time);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(-1.0f, -1.0f);
        GL11.glVertex2f(1.0f, -1.0f);
        GL11.glVertex2f(1.0f, 1.0f);
        GL11.glVertex2f(-1.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL20.glUseProgram(0);

        GL11.glPopMatrix();
    }

    private int createShader(String shaderSource, int shaderType) throws Exception {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);

            if(shader == 0)return 0;

            ARBShaderObjects.glShaderSourceARB(shader, shaderSource);
            ARBShaderObjects.glCompileShaderARB(shader);

            if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)throw new RuntimeException("Error creating shader: " + getLogInfo(shader));

            return shader;
        } catch (Exception exc) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            throw exc;

        }
    }

    public static ShaderUtil fromFile(File shaderFile) throws IOException {

        if(!shaderFile.exists()) {
            throw new FileNotFoundException("Fragment shader not found");
        }

        return new ShaderUtil(Files.lines(shaderFile.toPath()).collect(Collectors.joining("\n")));
    }

    private String fromFile(ResourceManager resourceManager, Identifier fileLocation) throws IOException {
        StringBuilder source = new StringBuilder();

        try (InputStream in = resourceManager.getResource(fileLocation).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                source.append(line).append('\n');
            }
        }
        return source.toString();
    }

    private String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
