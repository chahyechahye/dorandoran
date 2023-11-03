package com.doran.admin_voice.entity;

import com.doran.admin_voice.type.Genders;
import com.doran.content.entity.Content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_voice")
@AllArgsConstructor
@NoArgsConstructor
public class AdminVoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "av_id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "voice_url")
    private String voiceUrl;

    @Column(name = "voice_gender")
    @Enumerated(EnumType.STRING)
    private Genders voiceGender;

}
