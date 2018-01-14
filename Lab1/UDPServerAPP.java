import java.net.*;
import java.io.*;
public class UDPServerAPP{
	public static void main(String argv[]) {
		DatagramSocket socket;
		
		try {
		
			socket = new DatagramSocket(6000);
			byte[] mensaje_bytes = new byte[256];
			byte[] mensaje2_bytes = new byte[256];
			String mensaje ="";
			mensaje = new String(mensaje_bytes);
			String mensajeComp ="";
			DatagramPacket paquete = new DatagramPacket(mensaje_bytes,mensaje_bytes.length);
			DatagramPacket envpaquete;
			int puerto;
			InetAddress IPAdd;
			System.out.println("Bienvenido al Servido UDP de Luisa");
			System.out.println("ESpere mientras se entra el mensaje");
			do {
				socket.receive(paquete);
				
				mensaje = new String(mensaje_bytes);

				
				System.out.println(mensaje);
				
				if(mensaje.equals("1")){
					System.out.println("El cliente se fue");
				}
				else{
					puerto = paquete.getPort();
					IPAdd = paquete.getAddress();
					
					mensajeComp = mensaje.toUpperCase();
					mensaje2_bytes = mensajeComp.getBytes();
					envpaquete = new DatagramPacket(mensaje2_bytes,mensajeComp.length(),IPAdd,puerto);
					socket.send(envpaquete);
				}
				

			} while (1>0);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}