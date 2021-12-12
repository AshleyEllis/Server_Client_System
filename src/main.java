import java.io.*;
import java.util.Date;

public class main {

    public static void store(File i, File o) throws Exception{
        FileInputStream in = new FileInputStream(i);
        FileOutputStream out = new FileOutputStream(o);

        try{
            int n;
            while ((n = in.read()) != -1){
                out.write(n);
            }
        } finally {
            if (in != null){
                in.close();
            }

        }System.out.println("Stored");
    }
    public static void remove(String fName) throws FileNotFoundException{
        exists(fName);
        new File(fName).delete();
    }
    
    public static boolean found(String fName){
        File file = new File(fName);
        boolean result = file.exists();
        if(result && file.isFile()){
            return true;
        }
        else if (file.isDirectory()){
            return true;
        }else
            return false;
    }

    public static File create(String fName){
        File file = new File(fName);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return file;
    }


    public static void write(String fName, String text){
        File file= new File(fName);

        try{
            file=create(fName);

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {

                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String read(String fName) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        File file = new File(fName);
        exists(fName);
        try{
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try{
                String s;
                while ((s = in.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {

                in.close();
            }
        } catch(IOException e){
            throw  new RuntimeException(e);
        }
        return sb.toString();
    }
    

    private static void exists(String fName) throws FileNotFoundException{
        File file = new File(fName);
        if(!file.exists()){
            throw new FileNotFoundException(file.getName());

        }
    }




    private String data;
    private Date timestamp;

    public String getValue() {
        return data;
    }

    public void setValue(String value) {
        this.data = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(main e) {
        return data.equals(e.getValue());
    }

}
