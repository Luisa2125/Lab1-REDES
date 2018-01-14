import java.net.*;
import java.io.*;
 

public class UDPClientAPP{
	public static void main(String argv[]) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket socket;
		InetAddress address;
		byte[] mensaje_bytes = new byte[256];
		String mensaje="";
		mensaje_bytes=mensaje.getBytes();
		DatagramPacket paquete;
		String cadenaMensaje="";
		DatagramPacket servPaquete;
		byte[] RecogerServidor_bytes = new byte[256];
		try {
			socket = new DatagramSocket();
			address=InetAddress.getByName("localhost");
			do {
				mensaje = in.readLine();
				mensaje_bytes = mensaje.getBytes();
				paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
				socket.send(paquete);
				if(mensaje.equals("1")){
					System.out.println("Adios Cliente");
					System.exit(1);

				}
				RecogerServidor_bytes = new byte[256];
				servPaquete = new DatagramPacket(RecogerServidor_bytes,256);
				socket.receive(servPaquete);
				cadenaMensaje = new String(RecogerServidor_bytes);
				System.out.println(cadenaMensaje);
			} while (!mensaje.equals("1"));
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}