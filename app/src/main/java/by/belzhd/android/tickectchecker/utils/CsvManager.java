package by.belzhd.android.tickectchecker.utils;

public class CsvManager {

    public boolean convertToCsv(int type, List<String> value ) throws IOException {

        String [] record = null;
        String csv = value.get(0);
        String file_2 = "";
        boolean result = false;
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        //Create record
        switch(type) {
            case 1:
                //record = ("K41;" + "D" + value.get(1) + ";P" + value.get(2)).split(";");
                // break;
            case 2:
                //record = ("K40;" + "D" + value.get(1) + ";S"+value.get(2) + ";N" + value.get(3) + "P" + value.get(4)).split(";");
                record = value.get(1).split(";");
                //Write the record to file
                writer.writeNext(record);
                //close the writer
                writer.close();
                // result = RequestInExpress.sendRequest(false, csv,file_2);
                break;
            case 3:
                //record = ("K42;" + "D" + value.get(1) + ";S"+value.get(2) + ";N" + value.get(3) + "P" + value.get(4)).split(";");
                record = value.get(1).split(";");
                writer.writeNext(record);
                writer.close();
                file_2 = value.get(2);
                CSVWriter rep_writer = new CSVWriter(new FileWriter(file_2));
                for(int i=2; i<value.size();i++){
                    record = value.get(i).split(";");
                    rep_writer.writeNext(record);
                }
                rep_writer.close();
                //  result = RequestInExpress.sendRequest(true, csv,file_2);
                break;
        }

        return result;
    }

    public List<String[]>  convertFromCsv(String file) throws IOException {

        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1);
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        //Read CSV line by line and use the string array as you want
        return allRows;
    }
}
