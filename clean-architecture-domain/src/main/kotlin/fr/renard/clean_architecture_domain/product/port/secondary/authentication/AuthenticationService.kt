package fr.renard.clean_architecture_domain.product.port.secondary.authentication;

interface AuthenticationService {

  fun authenticate(login: String)

}
