package org.bgu.repository;

import org.bgu.repository.impl.AccessTokenRepositoryImpl;
import org.bgu.repository.impl.ApplicationUserRepositoryImpl;
import org.bgu.repository.impl.BguClientDetailsRepositoryImpl;
import org.bgu.repository.impl.RefreshTokenRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ApplicationUserRepositoryImpl.class, BguClientDetailsRepositoryImpl.class, AccessTokenRepositoryImpl.class, RefreshTokenRepositoryImpl.class } )
public class RepositoryBeans {

}
