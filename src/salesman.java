import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class salesman {
    public static void main(String [] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Dott> list = new HashMap<String,Dott>();
        System.out.println("dots:");

        while (true) {
            String s = reader.readLine();
            if(s.equals("end")) {
                break;
            }
            int dotx = Integer.parseInt(s);
            System.out.println(" ");
            int doty = Integer.parseInt(reader.readLine());
            System.out.println(" ");
            Dott d = new Dott(dotx,doty);
            list.put(""+dotx+doty, d);
            System.out.println("next");
        }

        System.out.println("Start point coordinates:");
        String startpointcoordinates = reader.readLine();
        startpointcoordinates = startpointcoordinates.replaceAll("\\s+","");
        Dott startpoint = list.get(startpointcoordinates);
        System.out.println("point: ("+list.get(startpointcoordinates).getX()+"; "+list.get(startpointcoordinates).getY()+")");
        String keyofwillbedeletedpoint = "";
        double allintervals = 0;
        while(true){
            for(int i=0; i< list.size()-1;) {
                Dott k = list.get(startpointcoordinates);
                list.remove(startpointcoordinates);
                double r = 100;
                int x=0;
                int y=0;
                keyofwillbedeletedpoint = "";
                for (Map.Entry<String, Dott> pair : list.entrySet()) {
                    double odledlosc = odleglosc(k, pair.getValue());
                    if (odledlosc < r) {
                        r = odledlosc;
                        x = pair.getValue().getX();
                        y = pair.getValue().getY();
                        keyofwillbedeletedpoint = pair.getKey();
                    }
                }
                System.out.println(r);
                System.out.println("point: ("+x+"; "+y+")");
                allintervals = allintervals+r;
                startpointcoordinates = keyofwillbedeletedpoint;
                if(list.size()==1){
                    allintervals = allintervals + odleglosc(startpoint,list.get(keyofwillbedeletedpoint));
                    System.out.println(odleglosc(startpoint,list.get(keyofwillbedeletedpoint)));
                    //System.out.println("point: ("+list.get(keyofwillbedeletedpoint).getX()+"; "+list.get(keyofwillbedeletedpoint).getY()+")");
                    System.out.println(allintervals);
                }
            }
            break;
        }

    }
    public static double odleglosc(Dott o, Dott s){
        double result = Math.sqrt((Math.pow(o.getX()-s.getX(), 2))+(Math.pow(o.getY()-s.getY(), 2)));
        return result;
    }
}