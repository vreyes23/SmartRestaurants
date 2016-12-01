package com.restuarants.smart.speechtotextwatsonv7;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCall;

/**
 * Created by oscarricaud on 11/30/16.
 */
public class ConversationStart {

    public ConversationStart(){
        ConversationService service = new ConversationService("2016-11-29");
        service.setUsernameAndPassword("2cd79c69-afac-4288-973a-120db0f1efef", "ENFhhydOkd5q");
        MessageRequest newMessage = new MessageRequest.Builder().inputText("I am hungry.").build();
        String sillyWorkspaceID = "25dfa8a0-0263-471b-8980-317e68c30488";
        MessageResponse response = service.message(sillyWorkspaceID, newMessage).execute();
        System.out.println(response);

    }
}
