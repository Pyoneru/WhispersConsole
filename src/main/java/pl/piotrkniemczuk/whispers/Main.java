package pl.piotrkniemczuk.whispers;

import pl.piotrkniemczuk.whispers.model.StartData;

import java.io.*;

public class Main {

    private static final String filename = "";

    public static void main(String[] args) throws IOException {
        StartData data = new StartData();
        if (args.length == 0) {
            loadFromFile(data, filename);
        } else {
            loadFromArgs(data, args);
        }
    }

    public static void loadFromFile(StartData data, String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] token = line.split("\\s+");
            if (token.length >= 2) {
                switch (token[0].toLowerCase()) {
                    case "server":
                        if (token[1].toLowerCase().equals("true") ||
                                token[1].toLowerCase().equals("1")) {
                            data.setServer(true);
                        }
                        break;

                    case "host":
                        data.setHost(token[1]);
                        break;

                    case "port":
                        try {
                            int port = Integer.parseInt(token[1]);
                            data.setPort(port);
                        } catch (NumberFormatException e) {
                            System.err.println("Bad port value");
                            System.exit(-1);
                        }
                        break;

                    case "nickname":
                        data.setNickname(token[1]);
                        break;
                }
            }
        }
    }

    public static void loadFromArgs(StartData data, String[] args){
        for(int i = 0; i < args.length; i++){
            switch(args[i].toLowerCase()){
                case "--server":
                case "-s":
                    data.setServer(true);
                    break;

                case "--host":
                case "-h":
                    if(i+1 < args.length){
                        data.setHost(args[i+1]);
                        i++;
                    }
                    break;

                case "--port":
                case "-p":
                    if(i+1 < args.length){
                        try{
                            int port = Integer.parseInt(args[i+1]);
                            data.setPort(port);
                        }catch (NumberFormatException e){
                            System.err.println("Bad port value");
                            System.exit(-1);
                        }
                    }
                    break;

                case "--nickname":
                case "-n":
                    if(i+1 < args.length){
                        data.setNickname(args[i+1]);
                    }
                    break;
            }
        }
    }
}
