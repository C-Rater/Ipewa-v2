package com.craterstudio.juanfrancrater.ipewa.ui.Meta.Interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.MetaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats.MetaContrat;

import java.util.ArrayList;

/**
 * Created by usuario on 31/05/18.
 */

public class EditMetaInteractor  implements MetaContrat.editMeta.Interactor {

    private EditMetaListener listener;

    public EditMetaInteractor(EditMetaListener listener) {
        this.listener = listener;
    }


    @Override
    public void EditMeta(String id, String name, String description, int color, String deadLine, String priority, String difficulty, String _idProyecto, String creator) {
        if (!name.isEmpty()) {
            MetaRepository.getInstance().edit(new Meta(id, name, description, color, deadLine, priority, difficulty, _idProyecto, creator));
            listener.back();
        } else {
            listener.showEmptyName();
        }
    }

    @Override
    public void getIdList() {
        listener.fillIdList(ProjectRepository.getInstance().getIdProjects(1));
    }

    public interface EditMetaListener {
        void back();

        void showEmptyName();

        void fillIdList(ArrayList<String> idProjects);
    }
}