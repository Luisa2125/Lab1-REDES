//librerias a usar
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
// clase server TCP
// Luisa Fernanda Arboleda
public class TCPServerAPP{
	
	public static void main (String argv[]){
		//se crea el socket
		ServerSocket socket;
		try {
			//inicializamos el socket en el puerto 6000
			socket = new ServerSocket(6000);
			System.out.println("Bienvenido al Servidor TCP de Luisa");
			TimeUnit.SECONDS.sleep(1);
			//time porque no me gusta que se impriman las cosas tan rapido
			System.out.println("Esperando conexion con cliente ...");
			//se realiza la conexion con el cliente
			Socket socket_cli = socket.accept();
			System.out.println("Conexion establecida.");
			//el objeto datainputstream nos sirve para leer mensajes que nos envia el servidor
			DataInputStream in = new DataInputStream(socket_cli.getInputStream());
			//el objeto dataoutputStrem nos sirve para enviar mensajes al cliente
			DataOutputStream upper =new DataOutputStream(socket_cli.getOutputStream());
			do {		
				String mensaje ="";
				//se le el mensaje que envia el cliente
				mensaje = in.readUTF();
				//envia uno se cierra el servidor
				if(mensaje.equals("1")){
					System.out.println("El cliente se fue");
				}else{
					System.out.println(mensaje);
					//convierte el mensaje a mayusculas
					String upperMen = mensaje.toUpperCase();
					//reenvia el mensaje en mayusculas al cliente
					upper.writeUTF(upperMen);
				}				
				
				
			} while (1>0);

		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}