package tools.updatemaven;

import java.io.File;

/**
 * delete files with the special end chars
 * @author liulu5
 *
 */
public class DeleteTempFile {

	private final String[] endFlags = new String[]{
			".repositories",
			".lastUpdated",
			".sha1",
			"lastUpdated.properties"
	};
	
	private void delete(String url){
		File file = new File(url);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				delete(f.getAbsolutePath());
			}
		}
		else{
			if(isDelete(file) == true){
				file.delete();
				System.out.println("deleted : " + file.getName());
			}
		}
	}
	
	private boolean isDelete(File file){
		for(String endFlag : endFlags){
			if(file.getName().endsWith(endFlag)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String url = "C:/Users/liulu5/.m2/repository";
		String url = "C:/Users/liulu5/.m2/repository/";
		new DeleteTempFile().delete(url);
		
	}

}
