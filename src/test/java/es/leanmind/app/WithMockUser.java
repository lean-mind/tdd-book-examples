package es.leanmind.app;

public @interface WithMockUser {
    String username() default Configuration.USERNAME;

    String password() default Configuration.PASSWORD;
}
