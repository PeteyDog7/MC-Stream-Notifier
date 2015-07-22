/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.server;

/* #%L
        * NanoHttpd-Samples
        * %%
        * Copyright (C) 2012 - 2015 nanohttpd
        * %%
        * Redistribution and use in source and binary forms, with or without modification,
        * are permitted provided that the following conditions are met:
        *
        * 1. Redistributions of source code must retain the above copyright notice, this
        *    list of conditions and the following disclaimer.
        *
        * 2. Redistributions in binary form must reproduce the above copyright notice,
        *    this list of conditions and the following disclaimer in the documentation
        *    and/or other materials provided with the distribution.
        *
        * 3. Neither the name of the nanohttpd nor the names of its contributors
        *    may be used to endorse or promote products derived from this software without
        *    specific prior written permission.
        *
        * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
        * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
        * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
        * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
        * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
        * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
        * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
        * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
        * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
        * OF THE POSSIBILITY OF SUCH DAMAGE.
        * #L%
        */

        import com.peteydog7.mcstreamnotifier.config.ConfigurationHandler;
        import com.peteydog7.mcstreamnotifier.reference.Config;
        import com.peteydog7.mcstreamnotifier.util.ChatComponents;
        import com.peteydog7.mcstreamnotifier.util.LogHelper;
        import fi.iki.elonen.NanoHTTPD;
        import net.minecraft.command.ICommandSender;
        import net.minecraft.util.EnumChatFormatting;
        import org.apache.commons.io.IOUtils;
        import sun.rmi.runtime.Log;

        import java.io.IOException;
        import java.io.StringWriter;
        import java.nio.charset.Charset;
        import java.util.Map;

/**
 * An example of subclassing NanoHTTPD to make a custom HTTP server.
 */
public class CallbackServer extends NanoHTTPD {

    private ICommandSender player;

    public CallbackServer(ICommandSender player) {
        super(8080);
        this.player = player;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        LogHelper.info("Server request received.");
        Map<String, String> parms = session.getParms();
        String msg = "<html><body>";
        if (parms.containsKey("fragment")){
            LogHelper.info("AJAX");
            if (parms.get("fragment")!=null) {
                String fragment = parms.get("fragment");
                LogHelper.info("fragment received: " + fragment);
                String authToken = fragment.split("=")[1];
                LogHelper.info("authToken received: " + authToken);
                ConfigurationHandler.configuration.get(Config.CATEGORY_TWITCH, Config.KEY_AUTH_TOKEN, "channel").set(authToken);
                ConfigurationHandler.update();
                authSuccess();
                return new Response(Response.Status.OK, MIME_PLAINTEXT, "success");
            }
            else {
                authFail();
                return new Response(Response.Status.OK, MIME_PLAINTEXT, "fail");
            }
        }
        LogHelper.info("Redirect");
        if (parms.containsKey("error")) {
            authFail();
            return new Response(Response.Status.OK, MIME_PLAINTEXT, "fail");
        }
        msg += "<script>var xmlhttp, result;if (window.XMLHttpRequest){xmlhttp=new XMLHttpRequest();}else{xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");}xmlhttp.onreadystatechange=function(){if (xmlhttp.readyState==4 && xmlhttp.status==200){result=xmlhttp.responseText;}if(result==\"success\"){document.write(\"<h3>MCStreamNotifier has now been authorized to use your twitch account.</h3><p>MCStreamNotifier can only access the users that are subscribed to your channel.</p><p>If you want to disable this app from accessing this information, please visit the 'connections' section of your twitch account settings page.</p>\");}else if(result==\"fail\"){document.write(\"<h3>MCStreamNotifier failed to get authorization to use your twitch account.</h3><p>Please try again. If issues persist, please visit <a href='https://github.com/PeteyDog7/MCStreamNotifier'>this link</a> to troubleshoot the issue.</p>\")}};xmlhttp.open(\"GET\",\"http://127.0.0.1:8080?fragment=\"+document.location.hash.substr(1),true);xmlhttp.send();</script>";
        msg += "<p id=\"result\"></p>";
        return new Response(Response.Status.OK, MIME_HTML, msg + "</body></html>\n");
    }

    private void authSuccess(){
        this.player.addChatMessage(ChatComponents.format(EnumChatFormatting.GREEN, "Authorization Successful!"));

    }

    private void authFail(){
        this.player.addChatMessage(ChatComponents.format(EnumChatFormatting.RED, "Authorization Failed! Please try again or visit the following link for help: "));
        this.player.addChatMessage(ChatComponents.newChatWithLinks("http://github.com/PeteyDog7/MCStreamNotifier"));
    }

}

