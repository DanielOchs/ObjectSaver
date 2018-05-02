package json.io.datareader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectSaver {

	// two "\\" are used for windows systems
	private static String path = "file/";

	private ObjectMapper mapper;
	private DataReader dr;
	
	public ObjectSaver() {
		mapper = new ObjectMapper();
		dr = new DataReader();
		
		// mapper Settings
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}

	/**
	 *
	 * @param c
	 *            type of class to be returned
	 * @return <T> T
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> T getObject(Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		//check for serverside files
		File file = new File(path + c.getSimpleName() + ".json");
		
		if (file.isFile() && file.length() > 0) {//check for serverside files

			Object obj;
			obj = mapper.readValue(file, c);
			
			System.out.println("eingelesen von file");
			return c.cast(obj);
		} 
		else 
		{//check for jar files
			Object obj;
			System.out.println(path + c.getSimpleName()+".json");
			obj = mapper.readValue(dr.getFileWithUtil(path + c.getSimpleName()+".json"), c);
			
			System.out.println("eingelesen von jar");
			return c.cast(obj);
		}
			

	}

	/**
	 * 
	 * @param c
	 *            type of classes to be returned
	 * @return List<T>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> List<T> getObjectList(Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		//check for serverside files
				File file = new File(path + c.getSimpleName() + "List.json");
				
				if (file.isFile() && file.length() > 0) {//check for serverside files

					Object obj;
					obj = mapper.readValue(file, List.class);
					
					System.out.println("eingelesen von file");
					return (List<T>)obj;
				} 
				else 
				{//check for jar files
					Object obj;
					obj = mapper.readValue(dr.getFileWithUtil(path + c.getSimpleName()+"List.json"), List.class);
					
					System.out.println("eingelesen von jar");
					return (List<T>)obj;
				}
	}

	/**
	 * 
	 * @param c
	 *            type of class to be returned
	 * @return String
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> String getObjectString(Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		return mapper.writeValueAsString(getObject(c));
	}

	/**
	 * 
	 * @param c
	 *            type of classes to be returned
	 * @return String
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> String getObjectListString(Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		return mapper.writeValueAsString(getObjectList(c));
	}

	/**
	 * 
	 * @param obj
	 *            object to save
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public void saveObject(Object obj) throws  IOException {
		(new File(path)).mkdirs();//create folder if not existing
		File file = new File(path + obj.getClass().getSimpleName() + ".json");
		file.createNewFile();
		
		mapper.writeValue(file, obj);
		 System.out.println("Objekt wurden erfolgreich geschrieben");

	}

	/**
	 * 
	 * @param objList
	 *            list of objects to save
	 * @param c
	 *            datatype used for objects in the list
	 * @throws IOException
	 */
	public <T> void saveObjectList(Object objList, Class<T> c) throws IOException {
		(new File(path)).mkdirs();//create folder if not existing
		File file = new File(path + c.getSimpleName() + "List.json");
		file.createNewFile();

		mapper.writeValue(file, objList);
		 System.out.println("Objekte wurden erfolgreich geschrieben");

	}

}
