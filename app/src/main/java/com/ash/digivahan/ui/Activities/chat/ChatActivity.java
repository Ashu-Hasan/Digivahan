package com.ash.digivahan.ui.Activities.chat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.ChatListAdapter;
import com.ash.digivahan.data.adapters.MyOrderItemAdapter;
import com.ash.digivahan.data.model.ChatItemModel;
import com.ash.digivahan.data.model.MyOrderItemModel;
import com.ash.digivahan.databinding.ActivityChatBinding;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    String TAG = "";

    ActivityChatBinding binding;

    ChatListAdapter chatListAdapter;
    ArrayList<ChatItemModel> chatItemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatListAdapter = new ChatListAdapter(ChatActivity.this, chatItemModels);
        binding.rvChatList.setAdapter(chatListAdapter);

    }
}