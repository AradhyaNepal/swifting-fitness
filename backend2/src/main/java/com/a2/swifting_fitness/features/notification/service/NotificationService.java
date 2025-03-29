package com.a2.swifting_fitness.features.notification.service;


import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.features.auth.repository.UsersRepository;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.mapper.DietToDietResponse;
import com.a2.swifting_fitness.features.notification.dto.AllNotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.NotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.SpecificUserNotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.UserNotificationResponse;
import com.a2.swifting_fitness.features.notification.entity.UserNotification;
import com.a2.swifting_fitness.features.notification.repository.FCMRepository;
import com.a2.swifting_fitness.features.notification.repository.UiNavigationRepository;
import com.a2.swifting_fitness.features.notification.repository.UserNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class NotificationService {
    final private UserNotificationRepository userNotificationRepository;
    final private FCMRepository fcmRepository;
    final private UsersRepository usersRepository;
    final private UiNavigationRepository uiNavigationRepository;

    public void sendToAllDevice(AllNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmRepository.sendMessageToToken(
                NotificationRequest.builder()
                        .topic("all")
                        .title(request.getTitle())
                        .body(request.getBody())
                        .image(request.getImage())
                        .page(request.getUiNavigateTo())
                        .data(request.getUiNavigateData())
                        .build()
        );
    }




    public void sendToSpecificUser(SpecificUserNotificationRequest request) throws CustomException, ExecutionException, InterruptedException {
        var user = usersRepository.findByUId(request.getUserUid());
        if (user.isEmpty()) {
            throw new CustomException(StringConstants.userNotFound, HttpStatus.NOT_FOUND);
        }
        var uiNavigation=uiNavigationRepository.findById(request.getUiNavigateTo());
        if(uiNavigation.isEmpty()){
            throw new CustomException(StringConstants.uiNavigationNotFound, HttpStatus.NOT_FOUND);
        }
        var userGet = user.get();
        var uiNavigationGet=uiNavigation.get();
        for (var item : userGet.getFcmToken()) {
            fcmRepository.sendMessageToToken(
                    NotificationRequest.builder()
                            .token(item)
                            .title(request.getTitle())
                            .body(request.getBody())
                            .image(request.getImage())
                            .page(request.getUiNavigateTo())
                            .data(request.getUiNavigateData())
                            .build()
            );
        }
        userNotificationRepository.save(
                UserNotification.builder()
                        .image(request.getImage())
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .onClickedGoToData(request.getUiNavigateData())
                        .onClickGoToWeb(request.isOnClickGoToWeb())
                        .body(request.getBody())
                        .createdAt(Instant.now())
                        .seen(false)
                        .userId(userGet)
                        .uiNavigationPath(uiNavigationGet)
                        .build()
        );

    }



    public Page<UserNotificationResponse> getNotificationResponse(int pageSize, int pageNumber){
        var pageable= PageRequest.of(pageNumber,pageSize);
        var data= userNotificationRepository.findAll(pageable);
        return data.map(UserNotificationResponse::map);
    }
    public void setNotificationSeen(String id) throws CustomException {
        var user= UserFromSecurityContext.get();
        var value=userNotificationRepository.findById(id);
        if(value.isEmpty()){
            throw new CustomException(StringConstants.noNotificationFound,HttpStatus.NOT_FOUND);
        }
        var notificationGet=value.get();
        if(notificationGet.getUserId().getId()!=user.getId()){
            throw new CustomException(StringConstants.notificationDoesNotBelongsToYou,HttpStatus.FORBIDDEN);
        }
        notificationGet.setSeen(true);
        userNotificationRepository.save(notificationGet);
    }


    public void setAllSeen() throws CustomException {
        var user= UserFromSecurityContext.get();
        var notifications=userNotificationRepository.getUserNotification(user.getId());
        for (var item : notifications){
            item.setSeen(true);
        }
        userNotificationRepository.saveAll(notifications);
    }
}
