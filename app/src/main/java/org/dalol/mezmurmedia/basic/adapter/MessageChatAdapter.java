/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.mezmurmedia.basic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.basic.holder.ItemViewHolder;
import org.dalol.model.chat.MessageChatModel;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/10/2016
 */
public class MessageChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageChatModel> mListOfFireChat;
    private static final int SENDER = 0;
    private static final int RECIPIENT = 1;

    public MessageChatAdapter(List<MessageChatModel> listOfFireChats) {
        mListOfFireChat = listOfFireChats;
    }

    @Override
    public int getItemViewType(int position) {
        if (mListOfFireChat.get(position).isSender()) {
            return SENDER;
        } else {
            return RECIPIENT;
        }
    }

    @Override
    public int getItemCount() {
        return mListOfFireChat.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SENDER:
                View viewSender = inflater.inflate(R.layout.sender_message_layout, viewGroup, false);
                viewHolder = new ItemViewHolder(viewSender);
                break;
            case RECIPIENT:
                View viewRecipient = inflater.inflate(R.layout.recipient_message_layout, viewGroup, false);
                viewHolder = new ItemViewHolder(viewRecipient);
                break;
            default:
                View viewSenderDefault = inflater.inflate(R.layout.sender_message_layout, viewGroup, false);
                viewHolder = new ItemViewHolder(viewSenderDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        MessageChatModel chatModel = mListOfFireChat.get(position);

        switch (viewHolder.getItemViewType()) {
            case SENDER:
                TextView senderView = (TextView) viewHolder.itemView.findViewById(R.id.senderMessage);
                senderView.setText(chatModel.getMessage());
                break;
            case RECIPIENT:
                TextView recipientView = (TextView) viewHolder.itemView.findViewById(R.id.recipientMessage);
                recipientView.setText(chatModel.getMessage());
                break;
        }
    }

    public void refillAdapter(MessageChatModel newFireChatMessage){

        /*add new message chat to list*/
        mListOfFireChat.add(newFireChatMessage);

        /*refresh view*/
        notifyItemInserted(getItemCount()-1);
    }

    public void refillFirsTimeAdapter(List<MessageChatModel> newFireChatMessage){

        /*add new message chat to list*/
        mListOfFireChat.clear();
        mListOfFireChat.addAll(newFireChatMessage);
        /*refresh view*/
        notifyItemInserted(getItemCount()-1);
    }

    public void cleanUp() {
        mListOfFireChat.clear();
    }
}