import React from 'react'
import { render } from 'react-dom'
import IndexLayout from "./IndexLayout";





const appTarget = document.createElement('div');
document.body.appendChild(appTarget);
render(
      <IndexLayout/>
    , appTarget
);

