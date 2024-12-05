package com.example.hello;

import com.example.hello.dto.ResponseDto;
import com.example.hello.dto.UserDto;
import com.example.hello.service.KnownUserService;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Api(name = "hello", version = "v1")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final KnownUserService knownUserService = new KnownUserService();

    @ApiMethod(name = "say_hello", httpMethod = ApiMethod.HttpMethod.GET, path = "hello")
    public ResponseDto sayHello(@Named("name") @Nullable String name) {
        logger.info("Saying hello to " + name);
        knownUserService.appendUser(name);
        String who = name == null ? "Google API" : name;
        return new ResponseDto("Hello " + who);
    }

    @ApiMethod(name = "protected", httpMethod = ApiMethod.HttpMethod.GET, path = "protected",
            authenticators = {EspAuthenticator.class}, audiences = {"261117802945-mrem026p93u4ce4hb4bg0tkmlikp2h5c.apps.googleusercontent.com "},
            clientIds = {"261117802945-mrem026p93u4ce4hb4bg0tkmlikp2h5c.apps.googleusercontent.com "})
    public ResponseDto getProtected(User user) throws UnauthorizedException {
        if (user == null) {
            logger.warn("Protected endpoint called without authentication");
            throw new UnauthorizedException("Invalid credentials");
        }
        logger.info("Protected endpoint called by User " + user.getId() + ", " + user.getEmail());
        return new ResponseDto("Access granted to " + user.getId());
    }

    @ApiMethod(name = "get_users", httpMethod = ApiMethod.HttpMethod.GET, path = "users")
    public List<UserDto> getUsers() {
        logger.info("Get users endpoint called");
        return knownUserService.getUsers().stream().map(u -> new UserDto(u.getName())).collect(Collectors.toList());
    }
}
