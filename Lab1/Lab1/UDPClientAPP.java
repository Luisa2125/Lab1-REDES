 //estas son las librerias a usar
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
// clase cliente UDP
// Luisa Fernanda Arboleda

public class UDPClientAPP{
	public static void main(String argv[]) {
		

		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		//se crea el socket cliente
		DatagramSocket socket;
		InetAddress address;
		/**a diferencia del server/cliente TCP el UDP recibe un paquete que contiene la informacion en un array de bytes
			para ello tenemos variables para la informacion a enviar y la de recibir**/
		byte[] mensaje_bytes = new byte[256];
		//esta variable es para leer la info del buffer
		String mensaje="";
		//se crea el paquete 
		DatagramPacket paquete;
		//esta variable es para enviar la info al servidor
		String cadenaMensaje="";
		//se crea el paquete a recibir
		DatagramPacket servPaquete;
		byte[] RecogerServidor_bytes = new byte[256];
		
		try {
			System.out.println("Hola Cliente TCP");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Cualquier mensajes que ingreses, el servidor te lo reenviara en mayusculas");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Si desea salir envie 1 y el cliente se desconectara del servidor");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Escribe tu mensaje");
			
			//se inicializa el socket
			socket = new DatagramSocket();
			address=InetAddress.getByName("localhost");
			do {
				//se lee el mensaje
				mensaje = teclado.readLine();
				//se convierte a bytes
				mensaje_bytes = mensaje.getBytes();
				//se inicializa el paquete
				paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
				//se envia
				socket.send(paquete);
				if(mensaje.equals("1")){
					//si envia 1 se desconecta
					System.out.println("Adios Cliente");
					System.exit(1);

				}
				//de lo contrario avanzamos a leer el paquete recibido desde el servido y lo imprimimos
				RecogerServidor_bytes = new byte[256];
				servPaquete = new DatagramPacket(RecogerServidor_bytes,256);
				socket.receive(servPaquete);
				cadenaMensaje = new String(RecogerServidor_bytes);
				System.out.println("El servidor envia: "+cadenaMensaje);
			} while (!mensaje.equals("1"));
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}