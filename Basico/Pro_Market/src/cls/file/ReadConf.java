package cls.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadConf {

    public Map<String,String> confBD() {

        Map<String,String> path = new HashMap<>();

        try {
            File file = new File("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\src\\conf\\PathBD");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            path.put("DRIVER",bufferedReader.readLine());
            path.put("URL",bufferedReader.readLine());
            path.put("USER",bufferedReader.readLine());
            path.put("PASS",bufferedReader.readLine());
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

}
