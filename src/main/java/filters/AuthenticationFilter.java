package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationFilter implements Filter {

    private String authToken;
/*
    public void updateToken(String newToken) {
        this.token = newToken;
    }
*/
    public AuthenticationFilter(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {
        requestSpec.header("Authorization", "Bearer " + authToken);
        return context.next(requestSpec,responseSpec);
    }
}
