import java.io.*; 
import java.nio.charset.StandardCharsets;

public class main {    
  
    public static void main(String[] args) throws IOException {
    	String path = "D:/java_lab/piano/src/source";
    	write(path);
          
    }
    private static void write(String path) throws FileNotFoundException, IOException{
    	
    	File file = new File(path);
    	File[] array = file.listFiles();
    	try (BufferedWriter a = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./reload.mcfunction"), StandardCharsets.UTF_8))) {
            try(BufferedWriter b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./tick.mcfunction"), StandardCharsets.UTF_8))){
            	try(BufferedWriter c = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./delete.mcfunction"), StandardCharsets.UTF_8))){
                    String t;
                    int index = 0;
                    for(int i = 0;i < array.length;i++) {
            	        String now = as(array[i].getName());
            	        if(!now.equals("item_frame"))
            	        {
            	            a.write(addobj(index,now));
            	            b.write(addtick(index));
            	            c.write(adddel(index++));
            	        }
                    }
                    a.write("scoreboard objectives add loymine_add dummy {\"text\":\"ÍÚ ¾ò °ñ\",\"color\":\"#FFAAAA\",\"bold\":true}\r\n" + 
                    		"scoreboard objectives setdisplay sidebar loymine_add");
                    c.write("scoreboard objectives remove loymine_add");
                }
            }
    	}
    }
    private static String addobj(int i,String id){
        return "scoreboard objectives add "+ "loymine_" + i + " minecraft.mined:"+id + "\n";
    }
    private static String adddel(int i){
        return "scoreboard objectives remove "+ "loymine_" + i + "\n";
    }
    private static String addtick(int i){
        String a = "scoreboard players add @a[scores={loymine_" + i +"=1..}] loymine_add 1\n";
        String b = "scoreboard players set @a[scores={loymine_"+ i +"=1..}] loymine_"+ i +" 0\n";
        return a+b;
    }
	private static String as(String scrStr) {
		String d = scrStr.replaceAll(".json", "");
		return d;
	}
     
}
