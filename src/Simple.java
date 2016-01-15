/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.naming.*;
import javax.naming.directory.*;
import javax.swing.*;
import java.util.Hashtable;

/**
 * Demonstrates how to create an initial context to an LDAP server
 * using simple authentication.
 *
 * usage: java Simple
 */
class Simple {
    public static void main(String[] args) {

        Hashtable<String, Object> env = new Hashtable<String, Object>(11);

        try {

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://"+ JOptionPane.showInputDialog(null, "IP-Adresse eingeben", "Michael Weinberger 5BHIT", JOptionPane.PLAIN_MESSAGE) +":389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            String username = JOptionPane.showInputDialog(null, "Bind-User angeben", "Michael Weinberger 5BHIT", JOptionPane.PLAIN_MESSAGE);
            env.put(Context.SECURITY_PRINCIPAL, "cn=" + username +",dc=nodomain,dc=com");
            env.put(Context.SECURITY_CREDENTIALS, JOptionPane.showInputDialog(null, "Passwort angeben", "Michael Weinberger 5BHIT", JOptionPane.PLAIN_MESSAGE));

            String group = JOptionPane.showInputDialog(null, "Gruppe angeben", "Michael Weinberger 5BHIT", JOptionPane.PLAIN_MESSAGE);

            System.out.println("Verbindungsversuch...");

            DirContext ctx = new InitialDirContext(env);
            System.out.println("OK");
            JOptionPane.showMessageDialog(null, "OK", "Michael Weinberger 5BHIT", JOptionPane.INFORMATION_MESSAGE);

            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            searchControls.setTimeLimit(30000);

            NamingEnumeration<?> namingEnum = ctx.search("cn=" + group + ",dc=nodomain,dc=com", "(objectclass=posixGroup)", searchControls);

            while (namingEnum.hasMore ()) {
                SearchResult result = (SearchResult) namingEnum.next ();
                Attributes attrs = result.getAttributes ();
                System.out.println("Authorization for group " + group + " " + (attrs.get("memberUID") != null &&
                        attrs.get("memberUid").contains(username) ? "OK" : "NOK"));
                JOptionPane.showMessageDialog(null, group + (attrs.get("memberUID") != null &&
                        attrs.get("memberUid").contains(username) ? " = OK" : " = NOK"), "Michael Weinberger 5BHIT", JOptionPane.INFORMATION_MESSAGE);
            }
            ctx.close();
            System.exit(0);

        } catch (NamingException e) {
            System.out.println("NOK");
            JOptionPane.showMessageDialog(null, "NOK", "Michael Weinberger 5BHIT", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("NOK");
            JOptionPane.showMessageDialog(null, "NOK", "Michael Weinberger 5BHIT", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}