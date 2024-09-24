package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthentificationFilter implements Filter {

    private final String token;

    public AuthentificationFilter(String token) {
        this.token = token;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {
        requestSpec.header("Authorization", token);
        return context.next(requestSpec,responseSpec);
    }
}
