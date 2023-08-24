package com.stage.logement.constant;
public class SecurityConstant {

	public static final String[] PUBLIC_URLS = { "/auth/login", "/auth/register", "/logement",}; // les urls sans authentification 
	public static final String AUTHORITIES = "authorities"; // stocke les roles 
	public static final String ISSUER = "Logement"; // le createur de Jwt
	public static final String AUDIENCE = "Stage"; // notre plateform ou le jwt est destiné
	public static final long EXPIRATION_TIME = 864_000_000; // 10 jours delai d'expiration exprimé en milliseconde 
	public static final String JWT_SECRET = "CHATO.D508£$$lkf125fk;sp!§?)KV?KGHLPX/JUNLDIF?°7490°9LCK/PV?O/X.NBIC?DPX/W¨µ$$"; // la clé secrete 
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified"; // affiche se mesg si la signature est incorrecte ou probleme au niveau de token il meme
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";// verifie disponibilité de ressource ou methode prise en charge par le serveur 
	public static final String JWT_PREFIX = "Bearer "; // le type de jeton ou préfixe precisé par le client dans l'envoie du token dans l'entête
	public static final String JWT_HEADER = "JWT"; // le nom de jeton 

}
