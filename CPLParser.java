import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CPLParser {
    public double parseScript(String fileName) throws CPLProgramNotFoundException, IOException, InvalidProgramEndException {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new CPLProgramNotFoundException("We can't find the program file.");
            }
            if (file.length() == 0) {
                throw new CPLProgramNotFoundException("The program file is empty.");
            }
            in = new BufferedReader(new FileReader(fileName));
            String str;

            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        }

        catch (CPLProgramNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        finally {
            if (in != null)
                in.close();
        }

        String result = new String(sb);
        String[] strArray = null;
        strArray = convertStrToArray(result);
        replace(strArray);
        double number = calculate(strArray);
        return number;
    }

    public static double calculate(String[] strArray) throws InvalidProgramEndException {
        double result = 0.0;

        try {
            for (int i = 0; i < strArray.length - 1; i += 2) {
                String[] tempList = {"enter", "add", "subtract", "multiply", "divide", "return"};
                boolean isContains = Arrays.asList(tempList).contains(strArray[i]);

                if (!strArray[0].toLowerCase().equals("enter")) {
                    throw new InvalidProgramEndException("The program does not start with enter.");
                }
                if (!strArray[strArray.length - 1].toLowerCase().equals("return")) {
                    throw new InvalidProgramEndException("The program does not end with return.");
                }
                if (!isContains) {
                    throw new InvalidProgramEndException("The program contains an unknown command.");
                }

                double tmp = Double.valueOf(strArray[i + 1]);

                if (strArray[i].toLowerCase().equals("add") || strArray[i].toLowerCase().equals("enter")) {
                    result = add(result, tmp);
                    continue;
                }
                if (strArray[i].toLowerCase().equals("subtract")) {
                    result = subtract(result, tmp);
                    continue;
                } else if (strArray[i].toLowerCase().equals("multiply")) {
                    result = multiply(result, tmp);
                    continue;
                } else if (strArray[i].toLowerCase().equals("divide")) {
                    result = divide(result, tmp);
                    continue;
                } else if (strArray[i].toLowerCase().equals("return")) {
                    return result;
                }

            }
        } 

        catch (InvalidProgramEndException e) {
            System.out.println(e);
        }

        return result;

    }

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        return num1 / num2;
    }

    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split("[;\\(]");
        return strArray;
    }

    public static void replace(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].replace(")", "");
            array[i] = array[i].trim();

        }

    }

    public static void main(String[] args) throws CPLProgramNotFoundException, InvalidProgramEndException, IOException {
        CPLParser parser = new CPLParser();
        String inputFile = "/Users/fjyfly/Documents/Java/04022/src/easy_prog7.txt";
        double result = parser.parseScript(inputFile);
        System.out.println(result);
    }
}

class CPLProgramNotFoundException extends Exception {
    public CPLProgramNotFoundException(String msg) {
        super(msg);
    }
}

class InvalidProgramEndException extends Exception {
    public InvalidProgramEndException(String msg) {
        super(msg);
    }
}

