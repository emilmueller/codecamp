package codecamp;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Array_Uebung {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int l=10;
		int n=20;
		int[] a = new int[l];
		for (int i = 0; i < a.length; i++) {
			a[i]=(int)(-(n+1)+Math.random()*2*(n+1));
		}
			
		
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
		
		
	}

}
