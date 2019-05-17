package ldap.vdirect;

/**
 * Created by DannyG on 28/02/2016.
 */
public class LdapAuthException extends  Exception {

    public LdapAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public LdapAuthException(String message) {
        super(message);
    }
}
