package main.systems.services;

import lombok.RequiredArgsConstructor;
import main.systems.entity.Customer;
import main.systems.entity.Role;
import main.systems.repositories.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceUser implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> getCustomerByUserName(String username) throws UsernameNotFoundException {
        return customerRepository.getCustomerByUserName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = getCustomerByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found",username)));
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomers();
    }
}
