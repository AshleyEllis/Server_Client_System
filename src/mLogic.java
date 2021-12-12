import java.io.File;
import java.io.IOException;

public class mLogic {
    private static final int ZERO = 0;
    private static final int TASK = 1;
    private int state = ZERO;
    private Boolean changed = false;
    private String path = "C:\\Users\\Ash\\IdeaProjects\\Os3\\src\\files\\";
    private String fileName="";
    private String userInput = "";

    public String Input(String userInput, cache store) throws IOException {
        String showClient =" ";

        if (state==ZERO) {
            showClient = "Please Type in Your Selection "; //waiting for the client
            state=TASK;
        }
        else if(state==TASK){
           String read =" ";
            if((!userInput.contains("update")) && (!userInput.contains("store")) ) {
                if(userInput.contains("list"))
                {
                    read = "list";
                }
                else if((!userInput.contains("create")) && (!userInput.contains("remove")) && (!userInput.contains("read")) && (!userInput.contains("list")))
                {
                    showClient = "Please select a prompt";
                }
                else
                {
                     if(!userInput.contains(" "))
                    {
                        showClient = "Please put the <filename>";
                    }
                    else
                    {
                        String[] input = userInput.split(" ");
                        read = input[0];
                        fileName = input[1];
                    }
                }
            }
            else {
                if( (userInput.contains("update")) || (userInput.contains("store")))
                {
                    if(!userInput.contains(" "))
                    {
                        showClient = "Please Select a prompt";
                    }
                    else
                    {
                        String[] input = userInput.split(" ", 3);
                        read = input[0];
                        fileName = input[1];
                        if( (input.length <= 2))
                        {
                            read = "!@#^%";
                        }
                        else
                        {
                            this.userInput = input[2];
                        }
                    }
                }
            }
            if(read.equalsIgnoreCase("create"))
            {
                if(main.found(path+fileName))
                {
                    showClient = "File exists";
                }
                else
                {
                    main.create(path+fileName);
                    showClient = fileName + " has been created";
                }
            }
            else if(read.equalsIgnoreCase("update")){
                if(!main.found(path+fileName))
                {
                    showClient = "Does not exist";
                }
                else
                {
                    main.create(path+fileName);
                    main.write(path+fileName, this.userInput);
                    showClient = fileName + "  has the value " + this.userInput;
                    changed = true;
                }

            }
            else if(read.equalsIgnoreCase("remove")) {
                if(!main.found(path+fileName))
                {
                    showClient = "Does not exist";
                }
                else
                {
                    main.remove(path+fileName);
                    showClient = fileName + " was removed";
                }

            }
            else if(read.equalsIgnoreCase("list"))
            {
                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                showClient="The files  are: ";
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        showClient += " " + listOfFiles[i].getName() + " ";
                    }
                }
            }
            else if(read.equalsIgnoreCase("read"))
            {
                if( (store.get(fileName) != null)  && !changed)
                {
                    showClient = "From cache: " + store.get(fileName);
                }
                else if(!main.found(path+fileName))
                {
                    showClient = "Does not exist";
                }
                else
                {
                    String all = new String();
                    all = main.read(path+fileName);
                    String trimSpace = all.trim();

                    int charC = 0;
                    for(int i = 0; i < trimSpace.length(); i++) {
                        if(trimSpace.charAt(i) != ' ')
                            charC++;
                    }

                    int wordC =0;
                    char c[]= new char[all.length()];
                    for(int i=0;i<all.length();i++)
                    {
                        c[i]= all.charAt(i);
                        if( ((i>0)&&(c[i]!=' ')&&(c[i-1]==' ')) || ((c[0]!=' ')&&(i==0)) )
                            wordC++;
                    }

                    int lineC = 0;
                    String[] lines = all.split("\r\n|\r|\n");
                    lineC = lines.length;
                    trimSpace = trimSpace.replace("\n", " ,");
                    showClient = "Characters: " + charC + " " + "Words: " + wordC + " " + "Lines: " + lineC + " " + "Contents: " + trimSpace + " ";
                    store.put(fileName, showClient);
                    changed = false;
                }

            }

            else if(read.equalsIgnoreCase("store"))
            {
                if( (main.found(path+ this.userInput)) && (main.found(path+fileName)) && (!fileName.equals(this.userInput)))
                {
                    String pathName = (path+fileName);
                    File storeFile = new File(pathName);
                    String dest = (path+ this.userInput);
                    File destination = new File(dest);

                    try {
                        main.store(storeFile, destination);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showClient = fileName + " was copied to " + this.userInput;
                    changed = true;
                }
                else if(fileName.equals(this.userInput))
                {
                    if(!fileName.contains(" "))
                    {
                        showClient = "Please enter a prompt";
                    }
                }
                else
                {
                    if(!this.userInput.contains(".txt"))
                    {
                        showClient = " does not contain the format .txt";
                    }
                    else
                    {
                        showClient =  this.userInput + " does not exist please create it first to copy into it";
                    }
                }

            }
            else if(read == "!@#^%")
            {
                showClient = "Please enter text";
            }
        }
     return showClient;
    }
}
