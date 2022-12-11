package src;

class Region {
    double h = 5*Math.sqrt(3);
    double b = 10;

    public static double[] drawRandom(int regNumber){
        double x = Math.random()*10 - 5;
        double y = Math.random()*5*Math.sqrt(3)-5*Math.sqrt(3)/2;
        while(!isInHexagon(x,y)){
            x = Math.random()*10 - 5;
            y = Math.random()*5*Math.sqrt(3)-5*Math.sqrt(3)/2;
        }
        if (regNumber==0) return new double[]{x,y}; // central
        else if(regNumber==1) return new double[]{x,y+5*Math.sqrt(3)}; //north
        else if(regNumber==2) return new double[]{x+7.5,y+5*Math.sqrt(3)/2}; //north east
        else if(regNumber==4) return new double[]{x,y-5*Math.sqrt(3)};//south east
        else if(regNumber==3) return new double[]{x+7.5,y-5*Math.sqrt(3)/2};//south
        else if(regNumber==5) return new double[]{x-7.5,y-5*Math.sqrt(3)/2};//south west
        else return new double[]{x-7.5,y+5*Math.sqrt(3)/2}; // north west

    }

    public static double[] getLocation(){
        int regNumber = (int)(Math.random()*7);
        return drawRandom(regNumber);
    }


    public static boolean isInHexagon(double x, double y){
        double x1 = -5; double y1 = 0;
        double x2 = -5/2; double y2 = 5*Math.sqrt(3)/2;
        double x3 = -5/2; double y3 = -5*Math.sqrt(3)/2;

        double x4 = 5; double y4 = 0;
        double x5 = 5/2; double y5 = 5*Math.sqrt(3)/2;
        double x6 = 5/2; double y6 = -5*Math.sqrt(3)/2;

        if (x <= x2){
            if (x*((y3-y1)/(x3-x1))+(y1*x3-y3*x1)/(x3-x1) <= y &&
            y <= x*((y2-y1)/(x2-x1))+(y1*x2-y2*x1)/(x2-x1)){
                return true;
            }else return false;
        }
        else if (x5 <= x){
            if (x*((y6-y4)/(x6-x4))+(y4*x6-y6*x4)/(x6-x4) <= y &&
            y <= x*((y5-y4)/(x5-x4))+(y4*x5-y5*x4)/(x5-x4)){
                return true;
            }else return false;
        }
        else return true;
    }
}


