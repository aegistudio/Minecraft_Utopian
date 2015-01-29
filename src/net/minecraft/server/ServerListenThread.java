package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.NetworkListenThread;

public class ServerListenThread extends Thread
{
    private final List<NetLoginHandler> pendingConnections = Collections.synchronizedList(new ArrayList<NetLoginHandler>());

    /**
     * This map stores a list of InetAddresses and the last time which they connected at
     */
    private final HashMap recentConnections = new HashMap();
    private int connectionCounter = 0;
    private final ServerSocket myServerSocket;
    private NetworkListenThread myNetworkListenThread;
    private final InetAddress myServerAddress;
    private final int myPort;

    public ServerListenThread(NetworkListenThread par1NetworkListenThread, InetAddress par2InetAddress, int par3) throws IOException
    {
        super("Listen thread");
        this.myNetworkListenThread = par1NetworkListenThread;
        this.myPort = par3;
        this.myServerSocket = new ServerSocket(par3, 0, par2InetAddress);
        this.myServerAddress = par2InetAddress == null ? this.myServerSocket.getInetAddress() : par2InetAddress;
        this.myServerSocket.setPerformancePreferences(0, 2, 1);
    }

    @SuppressWarnings("unused")
	public void processPendingConnections()
    {
        List<NetLoginHandler> var1 = this.pendingConnections;

        synchronized (this.pendingConnections)
        {
            for (int var2 = 0; var2 < this.pendingConnections.size(); ++var2)
            {
                NetLoginHandler netLoginHandler = (NetLoginHandler)this.pendingConnections.get(var2);

                try
                {
                    netLoginHandler.tryLogin();
                }
                catch (Exception var6)
                {
                    netLoginHandler.raiseErrorAndDisconnect("Internal server error");
                    this.myNetworkListenThread.getServer().getLogAgent().logWarningException("Failed to handle packet for " + netLoginHandler.getUsernameAndAddress() + ": " + var6, var6);
                }

                if (netLoginHandler.connectionComplete)
                {
                    this.pendingConnections.remove(var2--);
                }

                netLoginHandler.myTCPConnection.wakeThreads();
            }
        }
    }

    public void run()
    {
        while (this.myNetworkListenThread.isListening)
        {
            try
            {
                Socket socket = this.myServerSocket.accept();
                NetLoginHandler netLoginHandler = new NetLoginHandler(this.myNetworkListenThread.getServer(), socket, "Connection #" + this.connectionCounter++);
                this.addPendingConnection(netLoginHandler);
            }
            catch (IOException var3)
            {
                var3.printStackTrace();
            }
        }

        this.myNetworkListenThread.getServer().getLogAgent().logInfo("Closing listening thread");
    }

    @SuppressWarnings("unused")
	private void addPendingConnection(NetLoginHandler netLoginHandler)
    {
        if (netLoginHandler == null)
        {
            throw new IllegalArgumentException("Got null pendingconnection!");
        }
        else
        {
            List<NetLoginHandler> var2 = this.pendingConnections;

            synchronized (this.pendingConnections)
            {
                this.pendingConnections.add(netLoginHandler);
            }
        }
    }

    public void func_71769_a(InetAddress par1InetAddress)
    {
        if (par1InetAddress != null)
        {
            HashMap var2 = this.recentConnections;

            synchronized (this.recentConnections)
            {
                this.recentConnections.remove(par1InetAddress);
            }
        }
    }

    public void closeServerSocket()
    {
        try
        {
            this.myServerSocket.close();
        }
        catch (Throwable var2)
        {
            ;
        }
    }

    public InetAddress getInetAddress()
    {
        return this.myServerAddress;
    }

    public int getMyPort()
    {
        return this.myPort;
    }
}
