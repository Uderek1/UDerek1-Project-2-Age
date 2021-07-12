import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Age  {
    static String listTypeProperty;
    static String directoryProperty;
    static Object[][] ssaData = new Object[51][2]; // String, Object (could be LinkedList or ArrayList)

    public static void loadProperties(String[] args) throws FileNotFoundException, IOException {

        // Load Properties Files
        String rootPath = args[0];
        String appConfigPath = rootPath;

        File propertyFile = new File(rootPath);
        boolean propertyFileExists = propertyFile.exists();

        //System.out.println(directoryExists);
        if(!propertyFileExists){
            System.out.println("PropertyFile Not found.");
            System.exit(0);
        }

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        // Check if Properties file is valid

        listTypeProperty = appProps.getProperty("ListType");
        if(listTypeProperty == null){
            System.out.println("No ListType Property specified.");
            System.exit(0);
        }
        if((!listTypeProperty.equals("ArrayList") && !listTypeProperty.equals("LinkedList"))){
            System.out.println("Invalid value for ListType property.");
            System.exit(0);
        }

        directoryProperty = appProps.getProperty("Directory");
        if(directoryProperty == null){
            System.out.println("No Directory Property specified.");
            System.exit(0);
        }

        File directory = new File(directoryProperty);
        boolean directoryExists = directory.exists();
        if(!directoryExists){
            System.out.println("Invalid value for Directory property.");
            System.exit(0);
        }

        System.out.println("Properties loaded successfully.");
    }









    public static void main(String[] args) throws IOException {
        loadProperties(args);

        // Set up states abbreviations
        int stateNumber = 51;

        ssaData[0][0] = "AK";
        ssaData[1][0] = "AL";
        ssaData[2][0] = "AR";
        ssaData[3][0] = "AZ";
        ssaData[4][0] = "CA";
        ssaData[5][0] = "CO";
        ssaData[6][0] = "CT";
        ssaData[7][0] = "DC";
        ssaData[8][0] = "DE";
        ssaData[9][0] = "FL";
        ssaData[10][0] = "GA";
        ssaData[11][0] = "HI";
        ssaData[12][0] = "IA";
        ssaData[13][0] = "ID";
        ssaData[14][0] = "IL";
        ssaData[15][0] = "IN";
        ssaData[16][0] = "KS";
        ssaData[17][0] = "KY";
        ssaData[18][0] = "LA";
        ssaData[19][0] = "MA";
        ssaData[20][0] = "MD";
        ssaData[21][0] = "ME";
        ssaData[22][0] = "MI";
        ssaData[23][0] = "MN";
        ssaData[24][0] = "MO";
        ssaData[25][0] = "MS";
        ssaData[26][0] = "MT";
        ssaData[27][0] = "NC";
        ssaData[28][0] = "ND";
        ssaData[29][0] = "NE";
        ssaData[30][0] = "NH";
        ssaData[31][0] = "NJ";
        ssaData[32][0] = "NM";
        ssaData[33][0] = "NV";
        ssaData[34][0] = "NY";
        ssaData[35][0] = "OH";
        ssaData[36][0] = "OK";
        ssaData[37][0] = "OR";
        ssaData[38][0] = "PA";
        ssaData[39][0] = "RI";
        ssaData[40][0] = "SC";
        ssaData[41][0] = "SD";
        ssaData[42][0] = "TN";
        ssaData[43][0] = "TX";
        ssaData[44][0] = "UT";
        ssaData[45][0] = "VA";
        ssaData[46][0] = "VT";
        ssaData[47][0] = "WA";
        ssaData[48][0] = "WI";
        ssaData[49][0] = "WV";
        ssaData[50][0] = "WY";

        if(listTypeProperty.equals("ArrayList")){
            for(int i = 0; i < stateNumber; i++){

                File ssaFile = new File(directoryProperty + "\\" + ssaData[i][0] + ".txt");
                boolean ssaFileExists = ssaFile.exists();
                if(!ssaFileExists){
                    System.out.println("Could not find data for state: " + ssaData[i][0]);
                    continue;
                }

                ArrayList list=new ArrayList();

                BufferedReader br = new BufferedReader(new FileReader(ssaFile));
                String line = "";
                while((line = br.readLine()) != null){
                    list.add(line);
                }

                ssaData[i][1] = list;
            }
        }
        else{
            System.out.println("List Type not found");
        }
        int currentYear = 2021;
        String name="";
        String gender="";
        String state="";
        // user input
        while (name!="EXIT") {
            Scanner input = new Scanner(System.in);

            System.out.print("Name of the person (or EXIT to quit): ");
            name = input.nextLine();
            if(name.toUpperCase().equals("EXIT")){
                System.exit(0);
            }

            Boolean genderValid=false;
            while (!genderValid) {
                System.out.print("Gender (M/F): ");
                gender = input.nextLine();
                gender = gender.toUpperCase();
                if (gender.equals("M")||gender.equals("F")){
                    genderValid=true;
                }
                else {
                    System.out.println("Invalid Gender, Please Enter M or F");
                }
            }

            Boolean stateValid=false;
            int stateIndex=0;
            while (!stateValid) {
                System.out.print("State of birth (two-letter state code): ");
                state = input.nextLine();
                state = state.toUpperCase();
                for (int i = 0; i <stateNumber ; i++) {
                    if (ssaData[i][0].equals(state)){
                        stateValid=true;
                    }
                }
                if (!stateValid){
                    System.out.println("Invalid State, Please Try Again");
                }
            }

            int yearMajority = 0; // store whenver the most born with specified name
            int numberMajority = 0; // compared

            // Loop through our array list
            for (int i = 0; i < stateNumber; i++) {
                // if we find our matching state
                if (ssaData[i][0].equals(state)) {
                    ArrayList list = (ArrayList) ssaData[i][1];
                    String nameString = "," + name.toUpperCase() + ",";
                    String genderString = "," + gender + ",";

                    for (int j = 0; j < list.size(); j++) {
                        String dataString = ((String) list.get(j));
                        if (dataString.toUpperCase().contains(nameString) && dataString.contains(genderString)) {
                            String[] data = dataString.split(",");
                            int year = Integer.parseInt(data[2]);
                            int number = Integer.parseInt(data[4]);

                            if (number > numberMajority) {
                                yearMajority = year;
                                numberMajority = number;
                            }
                        }
                    }
                    break;
                }
            }

            if (yearMajority == 0 && numberMajority == 0) {
                System.out.println("No names for that gender found for that state.");
            } else {
                System.out.println(name + ", born in " + state + " is most likely around " + (currentYear - yearMajority) + " years old.");
            }
        }
    }
}
