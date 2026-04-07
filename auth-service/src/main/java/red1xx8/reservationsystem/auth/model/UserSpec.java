package red1xx8.reservationsystem.auth.model;

import org.springframework.data.jpa.domain.Specification;
import red1xx8.reservationsystem.auth.repository.UserEntity;

public class UserSpec {


    public static Specification<UserEntity> hasId(Long id){
        return (root,  query , criteriaBuilder) ->
                id == null? criteriaBuilder.conjunction():criteriaBuilder
                        .equal(root.get("id") , id);
    }

    public static Specification<UserEntity> hasUserName(String userName){
        return (root, query, criteriaBuilder) ->
                userName == null? criteriaBuilder.conjunction():criteriaBuilder
                        .equal(root.get("userName") , userName);
    }


    public static Specification<UserEntity> hasNumberPhone(String numberPhone){
        return (root, query, criteriaBuilder) ->
        numberPhone == null? criteriaBuilder.conjunction():criteriaBuilder
                        .equal(root.get("numberPhone") , numberPhone);
    }

    public static Specification<UserEntity> hasStatus(UserStatus status){
        return (root, query, criteriaBuilder) ->
                status == null? criteriaBuilder.conjunction():criteriaBuilder
                        .equal(root.get("status") , status);
    }


    public static Specification<UserEntity> hasRole(Roles role){
        return (root , query, criteriaBuilder) ->
                role == null? criteriaBuilder.conjunction():criteriaBuilder
                        .equal(root.get("role") , role);
    }



}
