import Modelo.ClsRequestDTO;

public class Global {
	
	public static class Auxiliar{
		public static ClsRequestDTO current_obj;
		public static String modo = "N";
		
		public static ClsRequestDTO getCurrent_obj() {
			return current_obj;
		}
		public static void setCurrent_obj(ClsRequestDTO pcurrent_obj) {
			current_obj = pcurrent_obj;
		}
		public static String getModo() {
			return modo;
		}
		public static void setModo(String pmodo) {
			modo = pmodo;
		}
	}
		
}
