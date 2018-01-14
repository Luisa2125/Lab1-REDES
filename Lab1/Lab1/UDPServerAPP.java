//estas son las librerias a usar
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
// clase server UDP
// Luisa Fernanda Arboleda
public class UDPServerAPP{
	public static void main(String argv[]) {
		//creamos el socket
		DatagramSocket socket;
		
		try {
		//inicializamos el socket en el puerto 6000
			socket = new DatagramSocket(6000);
			/**a diferencia del server/cliente TCP el UDP recibe un paquete que contiene la informacion en un array de bytes
			para ello tenemos variables para la informacion a recibir y la informacion a enviar**/
			byte[] mensaje_bytes = new byte[256];
			byte[] mensaje2_bytes = new byte[256];
			String mensaje ="";
			String mensajeComp ="";
			//el paquete a recibir se guarda en mensaje_bytes
			DatagramPacket paquete = new DatagramPacket(mensaje_bytes,mensaje_bytes.length);
			DatagramPacket envpaquete;
			int puerto;
			InetAddress IPAdd;
			System.out.println("Bienvenido al Servido UDP de Luisa");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Espere mientras entra el mensaje");

			do {
			//se recibe el paquete
				socket.receive(paquete);
				/**o convertimos, limpiando el buffer ya que puede que se sobre escriban ciertas palabras 
				pequeñas sobre una grande y la informacion no sea clara**/
				mensaje = new String(paquete.getData(), paquete.getOffset(), paquete.getLength());
				//se imprime el mensaje
				System.out.println(mensaje);
				
				if(mensaje.equals("1")){
					//si el cliente envia uno, significa que se desconecto
					System.out.println("El cliente se fue");
				}
				else{
					//de lo contrario avanzamos a responder el mensaje
					puerto = paquete.getPort();
					IPAdd = paquete.getAddress();
					//se convierte el mensaje a mayuscula
					mensajeComp = mensaje.toUpperCase();
					//se guarda en bytes
					mensaje2_bytes = mensajeComp.getBytes();
					//creamos el paquete con la informacion a enviar
					envpaquete = new DatagramPacket(mensaje2_bytes,mensajeComp.length(),IPAdd,puerto);
					//enviamos el paquete al cliente
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