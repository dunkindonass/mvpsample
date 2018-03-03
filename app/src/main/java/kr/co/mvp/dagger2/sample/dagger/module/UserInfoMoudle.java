package kr.co.mvp.dagger2.sample.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.mvp.database.UserInfoRepository;

/**
 * Created by my on 2016-12-22.
 */
@Module
public class UserInfoMoudle {


    @Provides
    @PerFragment
    UserInfoRepository proviceUserRepository() {
        return new UserInfoRepository();
    }

}
