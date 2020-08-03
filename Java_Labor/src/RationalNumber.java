
@SuppressWarnings({ "rawtypes", "serial" })
public class RationalNumber extends Number implements Comparable<RationalNumber> {

	private int zaehler;
	private int nenner;
	
	

	/**default constructor set zaehler to 0 and nenner to 1.
	 * 
	 */
	public RationalNumber() {
	this.zaehler = 0;
	this.nenner = 1;
}

	/**constructor to construct an object with given params.
	 * 
	 * @param zaehler
	 * @param nenner
	 */
	public RationalNumber(int zaehler, int nenner){
		if(nenner == 0){
			this.zaehler = 0;
			this.nenner = 1;
		}
		this.zaehler = zaehler;
		this.nenner = nenner;
		
		this.kuerzen();
		
	}

	/**method to cancel the rational number
	 * 
	 */
	private void kuerzen() {
		int ggT = ggT(this.zaehler, this.nenner);
		if (ggT ==1){				//no ggT, cannot do anything
			return;
		}
		else{
			int tempZaehler = this.zaehler/ggT;
			this.zaehler = tempZaehler;
			int tempNenner = this.nenner/ggT;
			this.nenner = tempNenner;
			return;
		}
	}
	
	/**
	 * method to find the greatest common divisor.
	 * @param first
	 * @param second
	 * @return int value of the greatest common divisor
	 */
	private int ggT(int first, int second ){
		
		while(second != 0){
		int temp = first%second;
		first = second;
		second = temp;
		}
		return first;
	}
	
	@Override
		public String toString() {
//			if(this == null){
//				String nothing  = "";
//				return nothing;
//			}
		String rationalNumber = String.valueOf(zaehler)+"/"+String.valueOf(nenner);
		if(this.nenner == 1){
		rationalNumber = String.valueOf(zaehler);	
		}
			
			return rationalNumber;
		}

	@Override
	public double doubleValue() {
		double result = (double) this.zaehler/this.nenner;
		return result;
	}

	@Override
	public float floatValue() {
		float result = (float) this.zaehler/this.nenner;
		return result;
	}

	@Override
	public int intValue() {
		
		int result = Math.round(this.zaehler/this.nenner);
		return result;
	}


	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(this== o){
			return true;
		}
		if(! (o instanceof RationalNumber)){
			return false;
		}
		
		RationalNumber other = (RationalNumber) o;
		
		return 		this.zaehler == other.nenner
				&&  this.zaehler == other.zaehler;
			
		}

	@Override
	public int compareTo(RationalNumber arg0) {
		this.kuerzen();
		arg0.kuerzen();
		
		
		this.gleichnamigMachen(this, arg0.nenner);
		arg0.gleichnamigMachen(arg0, this.nenner);
		
		if(this.zaehler<arg0.zaehler){
			return -1;
		}
		
//		if(this.zaehler < arg0.zaehler && this.nenner == arg0.nenner){
//			return -1;	
//		}
//		else if(this.zaehler == arg0.zaehler && this.nenner > arg0.nenner){
//			return -1;
//		}
//		
		else if(this.equals(arg0)){
		return 0;
		}
		else{
			return 1;
		}
//		
//		else if(this.zaehler > arg0.zaehler && this.nenner == arg0.nenner){
//		return 1;
//		}
//		else if(this.zaehler == arg0.zaehler && this.nenner < arg0.nenner){
//			return 1;
//		}
		
	}
	
	private RationalNumber gleichnamigMachen(RationalNumber a, int factor){
		this.nenner = a.nenner * factor;
		this.zaehler = a.zaehler * factor;
		return this;
	}
	
	public RationalNumber add(RationalNumber other){
		RationalNumber temp = new RationalNumber();
		temp.zaehler = this.zaehler;
		temp.nenner = this.nenner;
		
		temp.gleichnamigMachen(temp, other.nenner);
		other.gleichnamigMachen(other, this.nenner);
		
		temp.zaehler += other.zaehler;
		temp.kuerzen();
		return temp;
		
	}
	
	public RationalNumber subtract(RationalNumber other){
		RationalNumber temp = new RationalNumber();
		temp.zaehler = this.zaehler;
		temp.nenner = this.nenner;
		
		temp.gleichnamigMachen(temp, other.nenner);
		other.gleichnamigMachen(other, this.nenner);
		
		temp.zaehler -= other.zaehler;
		if(temp.zaehler < 0){
			temp.zaehler = 0;
			temp.nenner = 0;
		}
		temp.kuerzen();
		return temp;
	}
	public RationalNumber multiply(RationalNumber other){
		RationalNumber temp = new RationalNumber();
		temp.zaehler = this.zaehler;
		temp.nenner = this.nenner;
		
		temp.zaehler *= other.zaehler;
		temp.nenner *= other.nenner;
		
		temp.kuerzen();
		return temp;

	}
	
	public RationalNumber divide(RationalNumber other){
		RationalNumber temp = new RationalNumber();
		temp.zaehler = this.zaehler;
		temp.nenner = this.nenner;
		
		temp.zaehler *= other.zaehler;
		temp.nenner *= other.nenner;
		
		temp.kuerzen();
		return temp;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}
		
	}

