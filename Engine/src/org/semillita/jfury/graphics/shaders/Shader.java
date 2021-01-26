package org.semillita.jfury.graphics.shaders;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public class Shader {
	private String vertexShaderSrc = "#version 330 core\n" + 
			"	layout (location=0) in vec3 aPos;\n" + 
			"	layout (location=1) in vec4 aColor;\n" + 
			"	\n" + 
			"	out vec4 fColor;\n" + 
			"	\n" + 
			"	void main()\n" + 
			"	{\n" + 
			"		fColor = aColor;\n" + 
			"		gl_Position = vec4(aPos, 1.0);\n" + 
			"	}";
	
	private String fragmentShaderSrc = "#version 330 core\n" + 
			"	\n" + 
			"	in vec4 fColor;\n" + 
			"	\n" + 
			"	out vec4 color;\n" + 
			"	\n" + 
			"	void main()\n" + 
			"	{\n" + 
			"		color = fColor;\n" + 
			"	}";
	
	private int vertexID, fragmentID, shaderProgram;
	
	private float[] vertexArray = {
			0.5f,	-0.5f,	0.0f, 			1.0f,	0.0f,	0.0f,	1.0f,	//Bottom right
			-0.5f,	 0.5f,	0.0f, 			0.0f,	1.0f,	0.0f,	1.0f,	//Top left
			0.5f,	 0.5f,	0.0f, 			0.0f,	0.0f,	1.0f,	1.0f,	//Top right
			-0.5f,	-0.5f,	0.0f, 			1.0f,	1.0f,	0.0f,	1.0f	//Bottom left
			
	};
	
	private int[] elementArray = {
			2, 1, 0,	//Top right triangle
			0, 1, 3		//Bottom left triangle
	};
	
	private static int vaoID, vboID, eboID;
	
	public void init() {
		vertexID = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
		GL30.glShaderSource(vertexID, vertexShaderSrc);
		GL30.glCompileShader(vertexID);
		
		if(GL30.glGetShaderi(vertexID, GL30.GL_COMPILE_STATUS) == 0) {
			int logLength = GL30.glGetShaderi(vertexID, GL30.GL_INFO_LOG_LENGTH);
			System.out.println(GL30.glGetShaderInfoLog(vertexID, logLength));
		}
		
		fragmentID = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
		GL30.glShaderSource(fragmentID, fragmentShaderSrc);
		GL30.glCompileShader(fragmentID);
		
		if(GL30.glGetShaderi(fragmentID, GL30.GL_COMPILE_STATUS) == 0) {
			int logLength = GL30.glGetShaderi(fragmentID, GL30.GL_INFO_LOG_LENGTH);
			System.out.println(GL30.glGetShaderInfoLog(fragmentID, logLength));
		}
		
		shaderProgram = GL30.glCreateProgram();
		GL30.glAttachShader(shaderProgram, vertexID);
		GL30.glAttachShader(shaderProgram, fragmentID);
		GL30.glLinkProgram(shaderProgram);
		
		if(GL30.glGetProgrami(shaderProgram, GL30.GL_LINK_STATUS) == 0) {
			int logLength = GL30.glGetProgrami(shaderProgram, GL30.GL_INFO_LOG_LENGTH);
			System.out.println(GL30.glGetProgramInfoLog(shaderProgram, logLength));
		}
		
		vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
		vertexBuffer.put(vertexArray).flip();
		
		vboID = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboID);
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexBuffer, GL30.GL_STATIC_DRAW);
		
		IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
		elementBuffer.put(elementArray).flip();
		
		eboID = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, eboID);
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL30.GL_STATIC_DRAW);
		
		int positionsSize = 3;
		int colorSize = 4;
		int floatSizeBytes = 4;
		int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;
		GL30.glVertexAttribPointer(0, positionsSize, GL30.GL_FLOAT, false, vertexSizeBytes, 0);
		GL30.glEnableVertexAttribArray(0);
		
		GL30.glVertexAttribPointer(1, colorSize, GL30.GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
		GL30.glEnableVertexAttribArray(1);
		
		System.out.println("Shader init done");
		
	}

	public void update() {
		
		GL30.glUseProgram(shaderProgram);
		GL30.glBindVertexArray(vaoID);
		
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		
		GL30.glDrawElements(GL30.GL_TRIANGLES, elementArray.length, GL30.GL_UNSIGNED_INT, 0);
		
		
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		
		GL30.glBindVertexArray(0);
		GL30.glUseProgram(0);
	}
}
