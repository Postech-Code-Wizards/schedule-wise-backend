package com.scheduling.wise.config.graphql.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.context.MessageSource;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionResolver {

    private final MessageSource messageSource;

    public GraphQLExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment env) {
        if (exception instanceof CustomExceptionHandler ex) {
            String message = messageSource.getMessage(
                    ex.getErrorCode(),
                    ex.getArgs(),
                    "Erro desconhecido.",
                    env.getLocale()
            );

            GraphQLError error = GraphqlErrorBuilder.newError(env)
                    .message(message)
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();

            return Mono.just(List.of(error));
        }

        GraphQLError genericError = GraphqlErrorBuilder.newError(env)
                .message("Erro interno no servidor.")
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();

        return Mono.just(List.of(genericError));
    }
}
