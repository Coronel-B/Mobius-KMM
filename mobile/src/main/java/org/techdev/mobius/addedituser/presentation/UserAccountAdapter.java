package org.techdev.mobius.addedituser.presentation;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.techdev.mobius.R;
import org.techdev.mobius.users.domain.entities.UserAccount;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 10/1/2019
 *
 * PRO: Adapta la cuenta de usuario
 * PRE: Existe un solo ítem de cuenta en el recycler view
 */
public class UserAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * PRO: Define los 2 eventos que posee la cuenta de usuario
     * OBS: La cuenta de usuario NO es en versión UI porque la implementación de las escuchas tienen
     * operar con el objeto la caché; y UserAccountUi no tiene un atributo que dé id el cual pueda
     * buscar al objeto. Si tuviera una lista de cuentas ahí sí.
     */
    public interface UserAccountListener {
        /**
         * PRO: Escucha p/editar la cuenta de usuario
         * OBS: El evento es un click en el itemView.
         * @param userAccount: cuenta de usuario
         */
        void onAccountClick(UserAccount userAccount);

        /**
         * PRO: Escucha p/remover la cuenta de usuario
         * OBS: El evento es un click en el botón derecho
         */
        void onRemoveAccountClick();
    }

    private UserAccount userAccount;
    private UserAccountListener userAccountListener;
    private Resources resources;

    UserAccountAdapter(UserAccount userAccount, UserAccountListener userAccountListener, Resources resources) {
        this.userAccount = userAccount;
        this.userAccountListener = userAccountListener;
        this.resources = resources;
    }

    public class UserAccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView accountEmail, accountPasswordSecurity;
        ImageButton removeAccountButton;

        UserAccountViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.user_account_list_item, parent, false));
            accountEmail = itemView.findViewById(R.id.account_email);
            accountPasswordSecurity = itemView.findViewById(R.id.account_password_security);
            removeAccountButton = itemView.findViewById(R.id.remove_account_button);

//            Eventos
            removeAccountButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        /**
         * PRO: Escucha eventos en la cuenta de usuario
         * PRE: Funciona solo para un item de cuenta
         * OBS: Si tuviera más items tendría que considerar la posición de los mismos
         * @param view: item de cuenta de usuario
         */
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.remove_account_button:
                    userAccountListener.onRemoveAccountClick();
                    break;
                default:
                    userAccountListener.onAccountClick(getUserAccount());
                    break;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserAccountViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserAccountViewHolder accountViewHolder = (UserAccountViewHolder) viewHolder;
        UserAccount account = getUserAccount();

        accountViewHolder.accountEmail.setText(account.getEmail());
        accountViewHolder.accountPasswordSecurity.setText(account.getPasswordSecurity());

//        Si la contraseña es medianamente segura pinto el texto de naranja
        if (account.getPasswordSecurity().equals(resources.getString(R.string.fairly_safe_pw))) {
            accountViewHolder.accountPasswordSecurity.setTextColor(resources.getColor(R.color.colorOrange));
        } else {
            accountViewHolder.accountPasswordSecurity.setTextColor(resources.getColor(R.color.colorGreen));
        }
    }

    @Override
    public int getItemCount() {
        return getUserAccount() != null ? 1 : 0;
    }

    /**
     * PRO: Reemplaza una cuenta de usuario por la existente y lo notifica al adaptador
     */
    public void replaceData(UserAccount userAccount) {
        this.userAccount = userAccount;
        notifyDataSetChanged();
    }

    /**
     * PRO: Describe la cuenta de usuario
     * @return UserAccount: cuenta de usuario
     */
    private UserAccount getUserAccount() {
        return this.userAccount;
    }

}
