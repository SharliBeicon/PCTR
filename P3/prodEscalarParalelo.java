package P3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

public class prodEscalarParalelo implements Runnable{
    private static int tam = 1000000;
    private static int v[] = new int[tam];
    private static int w[] = new int[tam];
    public static ArrayList<Double> productoParcial = new ArrayList<Double>();
    private int _inicio;
    private int _fin;
    private int _idHebra;

    public prodEscalarParalelo(){};

    public prodEscalarParalelo(int idHebra, int inicio, int fin){
        _inicio = inicio;
        _fin = fin;
        _idHebra = idHebra;
    }

    public void rellenaVectores(){
        for(int i = 0; i < tam; i++){
            v[i] = i;
            w[i] = tam - i;
        }
    }

    public ArrayList<Double> getProductoParcial(){
        return productoParcial;
    }

    public void reseteaValores(){
        productoParcial.clear();
    }

    public void run(){
        productoParcial.add(_idHebra, 0.0);
        for (int i = _inicio; i < _fin; i++){
             productoParcial.set(_idHebra, productoParcial.get(_idHebra) + (v[i] * w[i]));
        }
    }

    public static void main (String args[]){
        prodEscalarParalelo pe = new prodEscalarParalelo();
        double productoTotal = 0.0;

        ExecutorService executor2 = Executors.newFixedThreadPool(2);
        ExecutorService executor4 = Executors.newFixedThreadPool(4);
        ExecutorService executor6 = Executors.newFixedThreadPool(6);
        ExecutorService executor8 = Executors.newFixedThreadPool(8);
        ExecutorService executor10 = Executors.newFixedThreadPool(10);

        pe.rellenaVectores();

        //PARA 2 HILOS
        long tiempo = System.currentTimeMillis();
        executor2.execute(new prodEscalarParalelo(0, 0, 500000));
        executor2.execute(new prodEscalarParalelo(1, 500000, 1000000));
        executor2.shutdown();   
        while(!executor2.isTerminated()){}
        
        for(int i = 0; i < pe.getProductoParcial().size(); i++){
            productoTotal = productoTotal + pe.getProductoParcial().get(i);
        }
        System.out.println(productoTotal);
        System.out.println("Dos hilos: " + (System.currentTimeMillis() - tiempo) + " Milisegundos");
        productoTotal = 0;
        pe.reseteaValores();
        //FIN PARA 2 HILOS

        //PARA 4 HILOS
        tiempo = System.currentTimeMillis();
        executor4.execute(new prodEscalarParalelo(0, 0, 250000));
        executor4.execute(new prodEscalarParalelo(1, 250000, 500000));
        executor4.execute(new prodEscalarParalelo(2, 500000, 750000));
        executor4.execute(new prodEscalarParalelo(3, 750000, 1000000));
        executor4.shutdown();   
        while(!executor4.isTerminated()){}

        for(int i = 0; i < pe.getProductoParcial().size(); i++){
            productoTotal = productoTotal + pe.getProductoParcial().get(i);
        }
        System.out.println(productoTotal);
        System.out.println("Cuatro hilos: " + (System.currentTimeMillis() - tiempo) + " Milisegundos");
        productoTotal = 0;
        pe.reseteaValores();
        //FIN PARA 4 HILOS

        //PARA 6 HILOS
        tiempo = System.currentTimeMillis();
        executor6.execute(new prodEscalarParalelo(0, 0, 166666));
        executor6.execute(new prodEscalarParalelo(1, 166666, 333332));
        executor6.execute(new prodEscalarParalelo(2, 333332, 499998));
        executor6.execute(new prodEscalarParalelo(3, 499998, 666664));
        executor6.execute(new prodEscalarParalelo(4, 666664, 833330));
        executor6.execute(new prodEscalarParalelo(5, 833330, 1000000));
        executor6.shutdown();   
        while(!executor6.isTerminated()){}

        for(int i = 0; i < pe.getProductoParcial().size(); i++){
            productoTotal = productoTotal + pe.getProductoParcial().get(i);
        }
        System.out.println(productoTotal);
        System.out.println("Seis hilos: " + (System.currentTimeMillis() - tiempo) + " Milisegundos");
        productoTotal = 0;
        pe.reseteaValores();
        //FIN PARA 6 HILOS

        //PARA 8 HILOS
        tiempo = System.currentTimeMillis();
        for(int i=0; i < 8; i++)
            executor8.execute(new prodEscalarParalelo(i, i*125000, (i+1)*125000));

        executor8.shutdown();   
        while(!executor8.isTerminated()){}

        for(int i = 0; i < pe.getProductoParcial().size(); i++){
            productoTotal = productoTotal + pe.getProductoParcial().get(i);
        }
        System.out.println(productoTotal);
        System.out.println("Ocho hilos: " + (System.currentTimeMillis() - tiempo) + " Milisegundos");
        productoTotal = 0;
        pe.reseteaValores();        
        //FIN PARA 8 HILOS

        //PARA 10 HILOS
        tiempo = System.currentTimeMillis();
        for(int i=0; i < 10; i++)
            executor10.execute(new prodEscalarParalelo(i, i*100000, (i+1)*100000));

        executor10.shutdown();   
        while(!executor10.isTerminated()){}

        for(int i = 0; i < pe.getProductoParcial().size(); i++){
            productoTotal = productoTotal + pe.getProductoParcial().get(i);
        }
        System.out.println(productoTotal);
        System.out.println("Diez hilos: " + (System.currentTimeMillis() - tiempo) + " Milisegundos");
        productoTotal = 0; 
        pe.reseteaValores();       
        //FIN PARA 8 HILOS
    }
};