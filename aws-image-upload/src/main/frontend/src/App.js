import React, {useState, useEffect, useCallback} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';

const UserProfiles = () => {

  const [userProfiles, setUserProfiles] = useState ([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res)
      setUserProfiles(res.data);
    });
  }

  useEffect(() => {
    fetchUserProfiles()
  }, []);

  return userProfiles.map((userProfile, index) => {
    return (
      <div key={index}>
        {/* imagem de perfil */}
        <br />
        <br />
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileId}</p>
        <Dropzone {...userProfile}/>
        <br />
      </div>
    )
  });
};

function Dropzone({ userProfileId}) {
  const onDrop = useCallback(acceptedFiles => {
    // Faça algo com os arquivos
    const file = acceptedFiles[0];
    console.log(file);
    
    const formData = new FormData();
    formData.append("file", file)

    axios.post(`http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => {
      console.log("upload de arquivos com sucesso")
    }).catch(err => {
      console.log(err);
    })
    
 
  }, []);
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Largue os arquivos aqui ...</p> :
          <p>Arraste 'n' soltar as imagens para seu perfil ou clique para selecionar imagens do perfil</p>
      }
    </div>
  )
}
function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
