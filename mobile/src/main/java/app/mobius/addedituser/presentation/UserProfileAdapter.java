package app.mobius.addedituser.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import app.mobius.mobius.R;
import app.mobius.users.domain.entities.UserProfile;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 11/1/2019
 *
 * PRO: Adapta al perfil de usuario
 * PRE: Existe un solo ítem de perfil en el recycler view
 */
public class UserProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * PRO: Define los 2 eventos que posee el perfil de usuario
     * OBS: El perfil de usuario NO es en versión UI porque la implementación de las escuchas tienen
     * operar con el objeto la caché; y UserProfileUi no tiene un atributo que dé id el cual pueda
     * buscar al objeto. Si con la versin UI se pudiera obtener a la versión normal ahí sí.
     */
    public interface UserProfileListener {
        /**
         * PRO: Escucha p/editar el perfil de usuario
         * OBS: El evento es un click en el itemView.
         * @param userProfile: perfil de usuario
         */
        void onProfileClick(UserProfile userProfile);

        /**
         * PRO: Escucha p/remover el perfil de usuario
         * OBS: El evento es un click en el botón derecho
         */
        void onRemoveProfileClick();
    }

    private final Context context;
    private UserProfile userProfile;
    private UserProfileListener userProfileListener;

    UserProfileAdapter(Context context, UserProfile userProfile, UserProfileListener userProfileListener) {
        this.context = context;
        this.userProfile = userProfile;
        this.userProfileListener = userProfileListener;
    }

    public class UserProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView profileImage;
        TextView fullProfileName, profileDni;
        ImageButton removeProfileButton;

        UserProfileViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.user_profile_list_item, viewGroup, false));
            profileImage = itemView.findViewById(R.id.profile_avatar_preview);
            fullProfileName = itemView.findViewById(R.id.full_profile_name);
            profileDni = itemView.findViewById(R.id.profile_dni);
            removeProfileButton = itemView.findViewById(R.id.remove_user_profile_button);

//            Eventos
            removeProfileButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        /**
         * PRO: Escucha eventos en el perfil de usuario
         * PRE: Funciona solo para un item de perfil
         * OBS: Si tuviera más items tendría que considerar la posición de los mismos
         * @param view: item de perfil de usuario
         */
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.remove_user_profile_button:
                    userProfileListener.onRemoveProfileClick();
                    break;
                default:
                    userProfileListener.onProfileClick(getUserProfile());
                    break;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserProfileViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserProfileViewHolder profileViewHolder = (UserProfileViewHolder) holder;
        UserProfile profile = getUserProfile();

        Glide.with(context)
                .load(profile.getAvatarPath())
                .apply(RequestOptions.centerCropTransform())
                .into(profileViewHolder.profileImage);

        String fullName = profile.getName() + " " + profile.getSurname();
        profileViewHolder.fullProfileName.setText(fullName);
        profileViewHolder.profileDni.setText(String.valueOf(profile.getDni()));
    }

    @Override
    public int getItemCount() {
        return getUserProfile() != null ? 1 : 0;
    }

    /**
     * PRO: Reemplaza un perfil de usuario por la existente y lo notifica al adaptador
     */
    public void replaceData(UserProfile userProfile) {
        this.userProfile = userProfile;
        notifyDataSetChanged();
    }

    /**
     * PRO: Describe el perfil de usuario
     * @return UserProfile: cuenta de usuario
     */
    private UserProfile getUserProfile() {
        return this.userProfile;
    }


}
