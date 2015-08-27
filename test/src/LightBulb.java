
public class LightBulb {
	public static void main(String[] args) {
		int array[] = new int[100];
		int k = 1;
		
		for(int i = 0; i < 100; i++){
			
			array[i] = 1;
		}
		
		for(int i = 2; i < 100; i++){
			k = 1;
		    while(k < 50){
		        if(array[2*k] == 1){
		            array[2*k] = 0;
		        }else{
		            array[2*k] = 1;
		        }
		        k++;
		    }
		}
		k = 0;
		for(int i = 0; i < 100; i++){
		       if(array[i] == 1){
		          k++;     
		        }
		}
		
		System.out.println("k="+k);
		System.out.println("kkkk");
	}
}
