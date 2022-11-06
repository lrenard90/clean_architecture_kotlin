package fr.renard.clean_architecture_domain.product.port.out.authentication;

interface AuthenticationService {

  fun authenticate(login: String)

}
