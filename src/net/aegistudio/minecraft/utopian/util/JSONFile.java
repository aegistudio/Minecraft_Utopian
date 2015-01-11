package net.aegistudio.minecraft.utopian.util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import net.aegistudio.json.JSONException;
import net.aegistudio.json.JSONInput;
import net.aegistudio.json.JSONObject;
import net.aegistudio.json.JSONParser;

public class JSONFile
{
	
	private final JSONObject json;
	
	public JSONFile(InputStream inputstream) throws Exception
	{
		DataInputStream jsoninput = new DataInputStream(inputstream);
		ArrayList<Character> jsondata = new ArrayList<Character>();
		while(true) try	{	jsondata.add((char) jsoninput.readByte());		}
		catch(EOFException exception)	{	break;		}
		jsoninput.close();
		
		final Character[] jsondata_array = jsondata.toArray(new Character[0]);

		this.json = new JSONParser().parse(new JSONInput()
		{
			int pointer = 0;
			
			@Override
			public boolean hasNext() throws JSONException
			{
				return pointer < jsondata_array.length;
			}
	
			@Override
			public char next() throws JSONException
			{
				return jsondata_array[pointer++];
			}
		});
	}
	
	public JSONFile(File file) throws Exception
	{
		this(new FileInputStream(file));
	}
	
	public JSONFile(String filename) throws Exception
	{
		this(new File(filename));
	}
	
	public JSONObject getObject()
	{
		return this.json;
	}
}
