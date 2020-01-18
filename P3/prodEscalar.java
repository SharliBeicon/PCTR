package P3;

public class prodEscalar{
    private int tam = 1000000;
    private int v[] = new int[tam];
    private int w[] = new int[tam];
    private double escalar = 0.0;

    public prodEscalar(){};

    public void rellenaVectores(){
        for(int i = 0; i < tam; i++){
            v[i] = i;
            w[i] = tam - i;
        }
    }

    public void realizaProdEscalar(){
        for (int i = 0; i < v.length; i++){
            escalar = escalar + (v[i] * w[i]);
        }
        System.out.println(escalar);
    }
    
    public static void main (String args[]){
        prodEscalar pe = new prodEscalar();

        pe.rellenaVectores();

        long tiempo = System.currentTimeMillis();
        pe.realizaProdEscalar();
        System.out.println(System.currentTimeMillis() - tiempo);
    }
};
